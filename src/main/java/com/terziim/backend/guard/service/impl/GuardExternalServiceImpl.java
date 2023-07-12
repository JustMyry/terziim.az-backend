package com.terziim.backend.guard.service.impl;


import com.terziim.backend.guard.constants.GuardConstants;
import com.terziim.backend.guard.service.GuardExternalService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GuardExternalServiceImpl implements GuardExternalService {



    @Override
    public Boolean verifyRequestFromUnUsualWords(String text, String regex) {
        List<String> request = Arrays.stream(text.split(regex)).toList();
        System.out.println("Terziim Politeness Guard ////////////////////////////////////////////");
        System.out.println(request);
        System.out.println(request.stream().anyMatch(s-> GuardConstants.getUnusalWords().contains(s)));
        System.out.println("/////////////////////////////////////////////////////////////////////");
        return request.stream().anyMatch(s-> GuardConstants.getUnusalWords().contains(s));
    }


}
