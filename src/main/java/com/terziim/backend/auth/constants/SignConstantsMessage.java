package com.terziim.backend.auth.constants;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignConstantsMessage {

    public static final String USERNAME_ALREADY_EXISTS = "Istifadeci adi ve ya nomre artiq istifade edilib.";
    public static final String EMAIL_SENT = "Nomreye mesaj SMS vasitesi ile gonderildi.";
    public static final long EXPIRATION_TIME_FOR_ACTIVATION_CODE = 432_000_000; // 5 days expressed in milliseconds

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String SELLER = "SELLER";

    public static final String ACTIVATION_CODE_IS_NOT_CORRECT = "Activation code is not correct";


}

