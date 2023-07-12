package com.terziim.backend.auth.constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignConstantsCode {

    public static final Integer SUCCESS = 100;
    public static final Integer AUTH_SERVICE_INTERNAL_ERROR = 600;
    public static final Integer EMAIL_SENT = 601;

    public static final Integer ACTIVATION_CODE_IS_NOT_CORRECT = 602;

}