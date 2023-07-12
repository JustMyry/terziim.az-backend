package com.terziim.backend.tekkart.dto.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpTekKartResponse {

    private Long id;

    private String buyerId;
    private String sellerId;

    private Long productId;
    private Long productPrice;
    private Long offeredPrice;
    private String productBrand;
    private String productName;

    private String buyerComment;
    private String payingType;

    private String status;

}
