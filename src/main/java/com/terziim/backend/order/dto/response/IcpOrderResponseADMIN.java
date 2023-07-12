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
public class IcpOrderResponseADMIN {


    private Long orderId;

    private Long offerMessageId;

    private Long productId;
    private String productName;
    private Integer count;
    private Float Price;
    private String buyerId;
    private String cargoType;
    private String payingType;
    private String buyerComment;
    private String sellerId;


    private Float cargoPrice;
    private String specialNote;

    private Boolean contactedToSeller;
    private String sellerAddress;
    private LocalDate transactionDate;


    private String status;
    private Boolean seen;
    private Boolean isActive;

}
