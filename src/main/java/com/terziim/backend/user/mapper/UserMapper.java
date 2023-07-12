package com.terziim.backend.user.mapper;

import com.terziim.backend.auth.dto.IcpSignUpAsAdmin;
import com.terziim.backend.auth.dto.IcpSignUpAsBuyer;
import com.terziim.backend.auth.dto.IcpSignUpAsSeller;
import com.terziim.backend.user.dto.request.IcpEditUser;
import com.terziim.backend.user.dto.request.IcpEditUserADMIN;
import com.terziim.backend.user.dto.request.IcpEditUserPassword;
import com.terziim.backend.user.dto.response.IcpUserResponse;
import com.terziim.backend.user.dto.response.IcpUserResponseADMIN;
import com.terziim.backend.user.model.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = getMapper(UserMapper.class);



    IcpUserResponse getSelfAccountFromUser(AppUser appUser);


    @Mapping(source = "bio", target = "bio")
    @Mapping(source = "profilePictureUrl", target = "profilePictureUrl")
    @Mapping(source = "howManyUserStared", target = "howManyUserStared")
    @Mapping(target = "relation", expression = "java(\"self\")")
    IcpUserResponse getEpAccountWithEmailFromUser(AppUser appUser);

    @Mapping(source = "bio", target = "bio")
    @Mapping(target = "email", expression = "java(null)")
    @Mapping(target = "address", expression = "java(null)")
    @Mapping(target = "phone", expression = "java(null)")
    @Mapping(source = "profilePictureUrl", target = "profilePictureUrl")
    @Mapping(source = "howManyUserStared", target = "howManyUserStared")
    IcpUserResponse getEpAccountWithoutEmailFromUser(AppUser appUser);

    @Mapping(source = "id", target = "id")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    @Mapping(target = "userId", source = "userId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(target = "verificationCode", source = "verificationCode")
    @Mapping(target = "userType", source = "userType")
    @Mapping(target = "bio", source = "bio")
    @Mapping(source = "profilePictureUrl", target = "profilePictureUrl")
    @Mapping(source = "privacy", target = "privacy")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "star", target = "star")
    @Mapping(source = "howManyUserStared", target = "howManyUserStared")
    @Mapping(source = "lastLoginDate", target = "lastLoginDate")
    @Mapping(source = "active", target = "isActive")
    @Mapping(source = "notLocked", target = "isNotLocked")
    @Mapping(source = "role", target = "role")
    @Mapping(source = "authorities", target = "authorities")
    IcpUserResponseADMIN getUserResponseForAdmin(AppUser user);

    void transferEditRequestToUserFromAdmin(IcpEditUserADMIN icpEditUser, @MappingTarget AppUser appUser);


    void transferEditRequestToUser(IcpEditUser icpEditUser, @MappingTarget AppUser appUser);

    void transferEditRequestToUserAsAdmin(IcpEditUserADMIN icpEditUser, @MappingTarget AppUser appUser);

    @Mapping(source = "newPassword", target = "password")
    void transferPasswordRequestToUser(IcpEditUserPassword userPassword, @MappingTarget AppUser appUser);


    AppUser getUserFromSignAsSeller(IcpSignUpAsSeller icpSignUpAsSeller);

    AppUser getUserFromSignAsBuyer(IcpSignUpAsBuyer icpSignUpAsBuyer);

    AppUser getUserFromSignAsAdmin(IcpSignUpAsAdmin icpSignUpAsAdmin);


}
