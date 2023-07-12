package com.terziim.backend.auth.dto;


import lombok.Data;

@Data
public class IcpSignUpAsAdmin {

    private String username;
    private String phone;

    private String code;

    private String password;


}
