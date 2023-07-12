package com.terziim.backend.tekkart.dto.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpTekKartRequest {

    private String jwt;

    private String sellerId;

    private Long productId;
    private Long productPrice;
    private Long offeredPrice;
    private String productBrand;
    private String productName;

    private String buyerComment;
    private String payingType;

}
