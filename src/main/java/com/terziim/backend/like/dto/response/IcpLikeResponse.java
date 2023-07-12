package com.terziim.backend.like.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpLikeResponse {

    private Long id;

    private Long productId;
    private String productName;
    private String productBrand;
    private Float productPrice;
    private String productPictureUrl;
    private String status;

}
