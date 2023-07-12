package com.terziim.backend.user.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.request.IcpSingleData;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.user.dto.request.IcpEditUser;
import com.terziim.backend.user.dto.request.IcpEditUserPassword;
import com.terziim.backend.user.dto.response.IcpUserResponse;

public interface UserService {


    IcpResponseModel<IcpUserResponse> getAccountInformationForUser(String username, IcpJustJwt data);

    IcpResponseModel<IcpUserResponse> getAccountInformationForGuest(String username);

    IcpResponseModel<IcpUserResponse> editUser(String username, IcpEditUser data);

    IcpResponseModel<String> editUserPassword(String username, IcpEditUserPassword epEditUserPassword);

    IcpResponseModel<String> editUserPrivacy(String username, IcpSingleData data);

    Boolean isUsernameExists(String username);

    IcpResponseModel<String> editUserGender(IcpJustJwt payload, String gender);

    IcpResponseModel<String> editUserProfilePicture(IcpJustJwt payload);

}
