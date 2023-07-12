package com.terziim.backend.auth.service.impl;

import com.terziim.backend.auth.dto.IcpSignUpAsAdmin;
import com.terziim.backend.auth.model.ActivationCode;
import com.terziim.backend.auth.constants.SignConstantsCode;
import com.terziim.backend.auth.constants.SignConstantsMessage;
import com.terziim.backend.auth.dto.IcpSignIn;
import com.terziim.backend.auth.dto.IcpSignUpAsBuyer;
import com.terziim.backend.auth.repository.ActivationCodeRepository;
import com.terziim.backend.auth.service.AuthService;
import com.terziim.backend.authority.model.Authority;
import com.terziim.backend.authority.repository.AuthRepository;
import com.terziim.backend.user.mapper.UserMapper;
import com.terziim.backend.exception.CustomException;
import com.terziim.backend.exception.StatusCode;
import com.terziim.backend.exception.StatusMessage;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.security.model.AppUserPrincipal;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.impl.UserExternalServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.terziim.backend.auth.constants.SignConstantsCode.AUTH_SERVICE_INTERNAL_ERROR;
import static com.terziim.backend.auth.constants.SignConstantsMessage.*;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserExternalServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final ActivationCodeRepository activationCodeRepository;
    public AuthServiceImpl(UserExternalServiceImpl userService, PasswordEncoder passwordEncoder, AuthRepository authRepository,
                           JwtProvider jwtProvider, AuthenticationManager authenticationManager, ActivationCodeRepository activationCodeRepository){
        this.userService=userService;
        this.passwordEncoder=passwordEncoder;
        this.authRepository=authRepository;
        this.jwtProvider=jwtProvider;
        this.authenticationManager=authenticationManager;
        this.activationCodeRepository=activationCodeRepository;
    }





    @Override
    public IcpResponseModel<String> signUp(IcpSignUpAsBuyer request) {
        if(!isRequestUnique(request.getUsername(), request.getPhone())){
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.builder().message(USERNAME_ALREADY_EXISTS).code(AUTH_SERVICE_INTERNAL_ERROR).build())
                    .build();
        }
        AppUser user = UserMapper.INSTANCE.getUserFromSignAsBuyer(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String activationCode = String.valueOf((int)(Math.random()*(10000-1+1)+1));
        setDetailsToUser(user, "BUYER", activationCode);
        signInTransactions(request.getUsername(), request.getPhone());
        userService.saveUser(user);
        createActivationCode(activationCode, user.getUserId(), new Date(System.currentTimeMillis() + EXPIRATION_TIME_FOR_ACTIVATION_CODE));
        return IcpResponseModel.<String>builder()
                .response(null)
                .status(IcpResponseStatus.builder().message(SignConstantsMessage.EMAIL_SENT).code(SignConstantsCode.EMAIL_SENT).build())
                .build();
    }


    @Override
    public IcpResponseModel<String> signUpAdmin(IcpSignUpAsAdmin request) {
        if(!isRequestUnique(request.getUsername(), request.getPhone())){
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.builder().message(USERNAME_ALREADY_EXISTS).code(AUTH_SERVICE_INTERNAL_ERROR).build())
                    .build();
        }
        AppUser user = UserMapper.INSTANCE.getUserFromSignAsAdmin(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setRoleToUser(user, ADMIN);
        user.setUserId(generateUserId());
        user.setNotLocked(true);
        user.setRole("ADMIN");
        user.setActive(true);
        signInTransactions(request.getUsername(), request.getPhone());
        userService.saveUser(user);
        AppUserPrincipal principal = new AppUserPrincipal(user);
        String token = jwtProvider.generateJWT(principal);
        return IcpResponseModel.<String>builder()
                .response(token)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
   @Transactional
    public IcpResponseModel<String> signInUser(IcpSignIn request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

        }catch (Exception ex){
            System.out.println(ex);
                throw new CustomException(StatusMessage.USERNAME_OR_PASSWORD_IS_INVALID, StatusCode.USERNAME_OR_PASSWORD_IS_INVALID);
        }
        AppUserPrincipal principal = getPrincipal(request.getUsername());
        if(!principal.isEnabled())
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getAccountIsNotEnabled())
                    .build();
        if(principal.banned())
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getAccountBanned())
                    .build();

        if(!principal.isAccountNonLocked())
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getAccountIsUnderLookup())
                    .build();
        String response = jwtProvider.generateJWT(principal);
        return IcpResponseModel.<String>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> activateUser(String userid, String code) {
        if(!verifyActivationCode(userid, code))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.builder().message(ACTIVATION_CODE_IS_NOT_CORRECT).code(SignConstantsCode.ACTIVATION_CODE_IS_NOT_CORRECT).build())
                    .build();
        AppUserPrincipal principal = getPrincipalwithUserid(userid);
        String token = jwtProvider.generateJWT(principal);
        AppUser user = userService.findAppUserByUserId(userid);
        user.setActive(true);
        userService.saveUser(user);
        return IcpResponseModel.<String>builder()
                .response(token)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    // Utility Methods #################################################################################################

    private boolean verifyActivationCode(String userid, String code) {
        ActivationCode activationCode = activationCodeRepository.findActivationCodeByUserName(userid);
        return activationCode != null && activationCode.getCode().equals(code);
    }


    // USER qeydiyyatdan kecerken bir defeye ozel cagirilir.
    // Meqsed qeydiyyatdan kecen USERe DBdan elaqesiz bir ID vermekdir
    // Burdaki meqsed ise USERin DB IDsinin tehlukesizlik meqsedi ile FrontEnd e bildirmemekdir.
    private String generateUserId(){
        return DateTimeFormatter.ISO_INSTANT.format(LocalDateTime.now().toInstant(ZoneOffset.UTC)).substring(2)
                .replace("T", "").replace("-", "").replace(":","").replace(".","");
    }


    // USER obyektinden UserDetails obyekti almaq ucun
    private AppUserPrincipal getPrincipal(String username){
        return new AppUserPrincipal(userService.findUserFromLogin(username));
    }


    // USER obyektinin IDsinden UserDetails obyekti almaq ucun
    private AppUserPrincipal getPrincipalwithUserid(String userId){
        return new AppUserPrincipal(userService.findAppUserByUserId(userId));
    }


    //Qeydiyyat isteyinde gelen 'username' in uygun olub olmadigini yoxlayiriq
    private boolean isRequestUnique(String username, String phone) {
        System.out.println(userService.findUserByUsername(username));
        return userService.findUserByUsername(username)==null && userService.findAppUserByPhone(phone)==null;
    }

    //Qeydiyyat isteyinde gelen 'username' in ve ya nomrenin tesdiqlenmemis olub olmadigini yoxlayiriq
    private void signInTransactions(String username, String phone) {
        AppUser userByUsername = userService.findAppUserByUsernameActiveFALSE(username);
        if(userByUsername != null){
            userService.deleteUser(userByUsername);
        }
        AppUser userByPhone = userService.findAppUserByPhoneActiveFALSE(phone);
        if(userByPhone != null){
            userService.deleteUser(userByPhone);
        }
    }


    //Gonderilen 'User'e gonderilen adda 'Role'u DBdan cekib verir
    private void setRoleToUser(AppUser user, String role) {
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authRepository.findByAuthority(role));
        user.setAuthorities(authorities);
    }


    //Istifadeci hesabini aktiv etsin deye aktivasiya kodu hazirlayir
    private void createActivationCode(String activationCode, String userid, Date date) {
        ActivationCode code = new ActivationCode(activationCode, userid, date);
        activationCodeRepository.save(code);
    }


    private void setDetailsToUser(AppUser user, String type, String activationCode) {
        setRoleToUser(user, USER);
        user.setUserId(generateUserId());
        user.setNotLocked(true);
        user.setVerificationCode(activationCode);
        user.setRole("USER");
        user.setActive(false);
        user.setUserType(type);
    }


}
