package com.terziim.backend.user.service;


import com.terziim.backend.user.dto.response.IcpUserResponse;
import com.terziim.backend.user.model.AppUser;

import java.util.List;

public interface UserExternalService {


    AppUser findAppUserByUserId(String userId);

    void saveUser(AppUser user);

    void deleteUser(AppUser user);

    AppUser findUserByUsername(String username);

    AppUser findUserFromLogin(String username);

    AppUser findAppUserByEmail(String email);

    boolean isJwtBelongToUser(String userId, String jwt);

    boolean isUserBlocked(String blocked, String blockedBy);

    public AppUser getUserWithJwt(String jwt);

    List<AppUser> findAppUserLikeUsername(String username);

    IcpUserResponse getResponseFromUser(AppUser user);

    void changeUserStar(AppUser user, Float star);

    AppUser findAppUserByPhoneActiveFALSE(String phone);

    AppUser findAppUserByUsernameActiveFALSE(String username);

    void makeUserLocked(AppUser user);

}