package com.terziim.backend.constants;

public class UserConstants {

    // Codes
    public static final Integer WARN_CODE = 707;
    public static final Integer ACCOUNT_BLOCKED_BY_USER_CODE = 650;
    public static final Integer QUOTE_WAS_NOT_FOUND_CODE = 651;
    public static final Integer USER_WAS_NOT_FOUND_CODE = 602;
    public static final Integer GENERAL_FAULT_CODE = 701;



    // Messages
    public static final String JWT_IS_NOT_BELONG_TO_USER = "Jwt which sent is not belong to user";
    public static final String ACCOUNT_BLOCKED_BY_USER = "The account which sent request blocked by user;";
    public static final String QUOTE_WAS_NOT_FOUND = "Quote was not found. Maybe Id is wrong.";
    public static final String USER_WAS_NOT_FOUND = "User was not found in exnotis DB.";
    public static final String GENERAL_FAULT = "General Fault in request";

}
