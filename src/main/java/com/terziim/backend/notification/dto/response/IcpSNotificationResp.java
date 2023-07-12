package com.terziim.backend.notification.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpSNotificationResp {


    private Long id;
    private String userId;

    private String notificationName;
    private String notificationDescription;
    private boolean seen;

    private String url;

}
