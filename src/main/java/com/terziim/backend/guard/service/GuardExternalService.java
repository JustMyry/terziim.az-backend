package com.terziim.backend.guard.service;

public interface GuardExternalService {


    Boolean verifyRequestFromUnUsualWords(String text, String regex);


}
