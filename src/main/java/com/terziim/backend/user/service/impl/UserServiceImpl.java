package com.terziim.backend.user.service.impl;


import com.terziim.backend.follow.service.impl.FollowExternalServiceImpl;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.request.IcpSingleData;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.requisition.service.RequisitionExternalService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.dto.request.IcpEditUser;
import com.terziim.backend.user.dto.request.IcpEditUserPassword;
import com.terziim.backend.user.dto.response.IcpUserResponse;
import com.terziim.backend.user.mapper.UserMapper;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.repository.UserRepository;
import com.terziim.backend.user.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static com.terziim.backend.constants.UserConstants.JWT_IS_NOT_BELONG_TO_USER;
import static com.terziim.backend.constants.UserConstants.WARN_CODE;

@Service
public class UserServiceImpl implements UserService {

    private final JwtProvider jwtProvider;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final FollowExternalServiceImpl followService;
    private final ProductExternalService productService;
    private final RequisitionExternalService requisitionService;
    public UserServiceImpl(JwtProvider jwtProvider, UserRepository repository, PasswordEncoder passwordEncoder
            , FollowExternalServiceImpl followService, ProductExternalService productService, RequisitionExternalService requisitionService){
        this.jwtProvider=jwtProvider;
        this.repository=repository;
        this.passwordEncoder = passwordEncoder;
        this.followService = followService;
        this.productService = productService;
        this.requisitionService = requisitionService;
    }



    @Override
    public IcpResponseModel<IcpUserResponse> getAccountInformationForUser(String username, IcpJustJwt data) {
        AppUser buyer = repository.findAppUserByUserId(jwtProvider.getSubject(data.getJwt()));
        AppUser seller = findUserByUsername(username);
        if(seller == null || buyer == null || !seller.isNotLocked()){
            return IcpResponseModel.<IcpUserResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        }
        if(buyer.getUserId().equals(seller.getUserId())){
            IcpUserResponse userAccount = UserMapper.INSTANCE.getEpAccountWithEmailFromUser(seller);
            setUserFollowStatistics(userAccount, seller);
            setUserProductStatistics(userAccount, seller);
            return IcpResponseModel.<IcpUserResponse>builder()
                    .response(userAccount)
                    .status(IcpResponseStatus.getSuccess())
                    .build();
        }
        IcpUserResponse externalUser = UserMapper.INSTANCE.getEpAccountWithoutEmailFromUser(seller);
        setUserFollowStatistics(externalUser, seller);
        setUserProductStatistics(externalUser, seller);
        externalUser.setRelation(followService.getRelation(seller.getUserId(), buyer.getUserId()));
        return IcpResponseModel.<IcpUserResponse>builder()
                .response(externalUser)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpUserResponse> getAccountInformationForGuest(String username) {
        AppUser user = findUserByUsername(username);
        if(user == null || !user.isNotLocked()){
            return IcpResponseModel.<IcpUserResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        }
        IcpUserResponse externalUser = UserMapper.INSTANCE.getEpAccountWithoutEmailFromUser(user);
        setUserFollowStatistics(externalUser, user);
        externalUser.setRelation("guest");
        return IcpResponseModel.<IcpUserResponse>builder()
                .response(externalUser)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<IcpUserResponse> editUser(String username, IcpEditUser data) {
        AppUser user = findUserByUsername(username);
        if(user == null || !isJwtBelongToUser(user.getUserId(), data.getJwt()) || !user.isNotLocked() || isUsernameExists(data.getUsername())){
            return IcpResponseModel.<IcpUserResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.builder().message(JWT_IS_NOT_BELONG_TO_USER).code(WARN_CODE).build())
                    .build();
        }
        UserMapper.INSTANCE.transferEditRequestToUser(data, user);
        saveUser(user);
        IcpUserResponse selfAccount = UserMapper.INSTANCE.getEpAccountWithEmailFromUser(user);
        setUserFollowStatistics(selfAccount, user);
        setUserProductStatistics(selfAccount, user);
        return IcpResponseModel.<IcpUserResponse>builder()
                .response(selfAccount)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<String> editUserPassword(String username, IcpEditUserPassword epEditUserPassword) {
        AppUser user = repository.findAppUserByUsername(username);
        if(user == null || !isJwtBelongToUser(user.getUserId(), epEditUserPassword.getJwt()) || !user.isNotLocked())
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        if(passwordEncoder.matches(epEditUserPassword.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(epEditUserPassword.getNewPassword()));
            saveUser(user);
        }
        else
            return IcpResponseModel.<String>builder()
                    .response("Password is invalid!")
                    .status(IcpResponseStatus.getPassIsWrong())
                    .build();
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> editUserPrivacy(String username, IcpSingleData data) {
        AppUser user = repository.findAppUserByUsername(username);
        if(user == null || !isJwtBelongToUser(user.getUserId(), data.getJwt()) || !checkPrivacyRequest(data.getData()) || !user.isNotLocked())
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        user.setPrivacy(data.getData());
        saveUser(user);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public Boolean isUsernameExists(String username) {
        if(repository.findAppUserByUsername(username)==null)
            return false;
        else
            return true;
    }

    @Override
    public IcpResponseModel<String> editUserGender(IcpJustJwt payload, String gender) {
        AppUser user = repository.findAppUserByUsername(jwtProvider.getSubject(payload.getJwt()));
        if(user == null || !user.isNotLocked() || !(gender.equals("kisi") && !gender.equals("qadin")))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        user.setGender(gender);
        repository.save(user);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> editUserProfilePicture(IcpJustJwt payload) {
        return IcpResponseModel.<String>builder()
                .response("This method does not exists. Code it!")
                .status(IcpResponseStatus.get404())
                .build();
    }




    //==================================================================================================================
    /**
     * @Target: Burdan sonraki metodlar User Service ucun yazilmis yardimci metodlardir.
     * @Comment: Bunlarin xaricle baglanditi yoxdur / olmamalidir.
     * @Comment: Bunlar sadece oxunurlugu artirmaq ucundur
     * */
    public AppUser getUserByUserId(String userId) {
        return repository.findAppUserByUserId(userId);
    }

    public AppUser findAppUserByUserId(String userId) {
        return repository.findAppUserByUserId(userId);
    }

    public void saveUser(AppUser user){
        repository.save(user);
    }

    public AppUser findUserByUsername(String username) {
        return repository.findAppUserByUsername(username);
    }

    public AppUser findAppUserByEmail(String email) {
        return repository.findAppUserByEmail(email);
    }

    private boolean isJwtBelongToUser(String userId, String jwt){
        return jwtProvider.getSubject(jwt).equals(userId);
    }

    public Integer getUserFollowingsCount(AppUser user) { return followService.getUserFollowingsCount(user.getUserId()); }

    public Integer getUserFollowersCount(AppUser user) {
        return followService.getUserFollowersCount(user.getUserId());
    }

    private void setUserFollowStatistics(IcpUserResponse account, AppUser user){
        account.setFollowers(getUserFollowersCount(user));
        account.setFollowings(getUserFollowingsCount(user));
    }

    private void setUserProductStatistics(IcpUserResponse selfAccount, AppUser user) {
        selfAccount.setProductCount(productService.getUsersProductCount(user.getUserId()));
    }

    private boolean checkPrivacyRequest(String data) {
        return data.equals("private") || data.equals("open");
    }



}
