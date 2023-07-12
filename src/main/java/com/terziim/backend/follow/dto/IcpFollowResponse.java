package com.terziim.backend.follow.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpFollowResponse {

    private String userId;
    private String username;
    private String profilePictureUrl;

}
