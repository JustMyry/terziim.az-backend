package com.terziim.backend.like.dto.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpLikeRequest {

    private String jwt;
    private Long productId;

}
