package com.terziim.backend.notification.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpCNotificationResp {

    private Long id;
    private String userId;

    private Long commentId;
    private String comment;
    private boolean seen;

    private String productId;
}
