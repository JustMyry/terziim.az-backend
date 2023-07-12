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
public class IcpOrderResponseBUYER {

    private Long offerMessageId;
    private Long orderId;

    private Long productId;
    private String productBrand;
    private String productName;
    private String productPictureUrl;

    private String buyerComment;

    private Integer count;
    private Float price;
    private Float cargoPrice;

    private LocalDate createdAt;
    private String status;
    private Boolean isActive;

}
