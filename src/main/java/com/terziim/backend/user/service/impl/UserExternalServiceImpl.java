package com.terziim.backend.user.service.impl;


import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.dto.response.IcpUserResponse;
import com.terziim.backend.user.mapper.UserMapper;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.repository.UserRepository;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserExternalServiceImpl implements UserExternalService {


    private final UserRepository repository;
    private final JwtProvider jwtProvider;
    public UserExternalServiceImpl(UserRepository repository, JwtProvider jwtProvider){
        this.repository = repository;
        this.jwtProvider=jwtProvider;
    }


    @Override
    @Transactional
    public AppUser findAppUserByUserId(String userId) {
        return repository.findAppUserByUserId(userId);
    }

    @Override
    public void saveUser(AppUser user){
        repository.save(user);
        return;
    }

    @Override
    public void deleteUser(AppUser user) {
        repository.delete(user);
    }

    @Override
    @Transactional
    public AppUser findUserByUsername(String username) {
        return repository.findAppUserByUsername(username);
    }

    @Override
    @Transactional
    public AppUser findUserFromLogin(String username) {
        return repository.findUserFromLogin(username);
    }

    @Override
    @Transactional
    public AppUser findAppUserByEmail(String email) {
        return repository.findAppUserByEmail(email);
    }


    @Transactional
    public AppUser findAppUserByPhone(String phone) {
        return repository.findAppUserByPhone(phone);
    }


    @Override
    @Transactional
    public boolean isJwtBelongToUser(String userId, String jwt){
        return jwtProvider.getSubject(jwt).equals(userId);
    }


    @Override
    @Transactional
    public boolean isUserBlocked(String blocked, String blockedBy) {
        return false;
    }


    @Override
    @Transactional
    public AppUser getUserWithJwt(String jwt){
        return repository.findAppUserByUserId(jwtProvider.getSubject(jwt));
    }


    @Override
    @Transactional
    public List<AppUser> findAppUserLikeUsername(String username) {
        return repository.findAppUserLikeUsername(username);
    }

    @Override
    public IcpUserResponse getResponseFromUser(AppUser user) {
        return UserMapper.INSTANCE.getEpAccountWithoutEmailFromUser(user);

    }

    @Override
    public void changeUserStar(AppUser user, Float star) {
        user.setStar(star);
        repository.save(user);
    }

    @Override
    public AppUser findAppUserByPhoneActiveFALSE(String phone) {
        return repository.findAppUserByPhoneActiveFALSE(phone);
    }

    @Override
    public AppUser findAppUserByUsernameActiveFALSE(String username) {
        return repository.findAppUserByUsernameActiveFALSE(username);
    }

    @Override
    public void makeUserLocked(AppUser user) {
        user.setNotLocked(false);
        repository.save(user);
    }


}
