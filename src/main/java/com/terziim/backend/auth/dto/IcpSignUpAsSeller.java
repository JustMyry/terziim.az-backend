package com.terziim.backend.auth.dto;


import lombok.Data;

@Data
public class IcpSignUpAsSeller {


    private String username;
    private String phone;
    private String password;

    private String requisitionHeader;
    private String requisitionText;
}
