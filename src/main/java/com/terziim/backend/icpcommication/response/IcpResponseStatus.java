package com.terziim.backend.icpcommication.response;


import com.terziim.backend.exception.StatusCode;
import com.terziim.backend.exception.StatusMessage;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpResponseStatus {

    private Integer code;
    private String message;

    public static IcpResponseStatus getSuccess(){
        return IcpResponseStatus.builder()
                .code(StatusCode.SUCCESS)
                .message(StatusMessage.SUCCESS)
                .build();
    }

    public static IcpResponseStatus getRequestIsInvalid(){
        return IcpResponseStatus.builder()
                .code(StatusCode.REQUEST_IS_INVALID)
                .message(StatusMessage.REQUEST_IS_INVALID)
                .build();
    }


    public static IcpResponseStatus getJwtIsInvalid(){
        return IcpResponseStatus.builder()
                .code(StatusCode.JWT_IS_NOT_VALID)
                .message(StatusMessage.JWT_IS_NOT_VALID)
                .build();
    }


    public static IcpResponseStatus getPassIsWrong(){
        return IcpResponseStatus.builder()
                .code(StatusCode.PASSWORD_IS_WRONG)
                .message(StatusMessage.PASSWORD_IS_WRONG)
                .build();
    }


    public static IcpResponseStatus get404(){
        return IcpResponseStatus.builder()
                .message("Sehife bu kainatda movcud deyildir, paralel olmayan basqa birini yoxlayiniz :)")
                .code(404)
                .build();
    }


    public static IcpResponseStatus getMessageSent(){
        return IcpResponseStatus.builder()
                .code(StatusCode.SUCCESS)
                .message("Message Sent")
                .build();
    }


    public static IcpResponseStatus getOfferSent(){
        return IcpResponseStatus.builder()
                .code(StatusCode.SUCCESS)
                .message("Offer Sent")
                .build();
    }


    public static IcpResponseStatus getUserNotFound(){
        return IcpResponseStatus.builder()
                .code(StatusCode.USER_NOT_FOUND)
                .message("Istifadeci tapilmadi")
                .build();
    }

    public static IcpResponseStatus getAccountIsNotEnabled() {
        return IcpResponseStatus.builder()
                .code(StatusCode.USER_IS_NOT_ENABLED)
                .message("Istifadeci telefon nomresini tesdiqlemeyib")
                .build();
    }

    public static IcpResponseStatus getAccountBanned() {
        return IcpResponseStatus.builder()
                .code(StatusCode.USER_BANNED)
                .message("Istifadecinin Terziim Istifade Sozlesmesini pozdugu dusunulduyu ucun hesabi Banlanmisdir.")
                .build();
    }

    public static IcpResponseStatus getAccountIsUnderLookup() {
        return IcpResponseStatus.builder()
                .code(StatusCode.USER_IS_UNDER_LOOKUP)
                .message("Istifadecinin Terziim Istifade Sozlesmesini pozdugu dusunulduyu ucun hesabi Incelemeye alinmisdir.")
                .build();
    }
}
