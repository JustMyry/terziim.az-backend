package com.terziim.backend.auth.service;

import com.terziim.backend.auth.dto.IcpSignIn;
import com.terziim.backend.auth.dto.IcpSignUpAsAdmin;
import com.terziim.backend.auth.dto.IcpSignUpAsBuyer;
import com.terziim.backend.auth.dto.IcpSignUpAsSeller;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import jakarta.mail.MessagingException;

public interface AuthService {

    IcpResponseModel<String> signUp(IcpSignUpAsBuyer request) throws MessagingException;

    IcpResponseModel<String> signUpAdmin(IcpSignUpAsAdmin request);

    IcpResponseModel<String> signInUser(IcpSignIn request);

    IcpResponseModel<String> activateUser(String userid, String code);
}

