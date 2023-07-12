package com.terziim.backend.notification.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpNotificationResponse<T> {


    private String notificationType;
    private String userId;

    private T t;
}
