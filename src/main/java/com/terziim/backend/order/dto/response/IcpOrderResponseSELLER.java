package com.terziim.backend.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpOrderResponseSELLER {


    private Long offerMessageId;
    private Long orderId;

    private String buyerId;
    private String buyerComment;

    private Long productId;
    private String productBrand;
    private String productName;
    private String productPictureUrl;

    private Integer count;
    private Float price;
    private Float cargoPrice;

    private LocalDate createdAt;
    private String status;
    private Boolean isActive;

}
