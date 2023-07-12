package com.terziim.backend.exception;

public class StatusCode {

    public static final Integer USER_IS_NOT_ENABLED = 01;
    public static final Integer USER_BANNED = 02;
    public static final Integer USER_IS_UNDER_LOOKUP = 03;
    public static Integer SUCCESS = 100;
    public static Integer INTERNAL_SERVER_ERROR = 101;
    public static Integer USER_NOT_FOUND = 102;
    public static Integer USER_ALREADY_EXISTS = 103;
    public static Integer ROLE_NOT_FOUND = 104;
    public static Integer EMAIL_HAS_NOT_SENT = 105;
    public static Integer TOKEN_NOT_FOUND = 106;
    public static Integer TOKEN_HAS_EXPIRED = 107;
    public static Integer VALIDATION_ERROR = 108;
    public static Integer USERNAME_OR_PASSWORD_IS_INVALID = 109;
    public static Integer REFRESH_TOKEN_IS_NOT_VALID = 110;
    public static Integer REFRESH_TOKEN_NOT_FOUND = 111;
    public static Integer REFRESH_TOKEN_USER_IS_NOT_INVALID = 112;
    public static Integer JWT_IS_NOT_VALID = 113;
    public static Integer JWT_HAS_EXPIRED = 114;

    public static Integer REQUEST_IS_INVALID =  115;

    public static Integer PASSWORD_IS_WRONG = 116;



}
