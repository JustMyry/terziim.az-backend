package com.terziim.backend.order.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpOrderRequest {

    private String jwt;

    private Long productId;

    private Integer count;
    private String  size;
    private String buyerComment;

}
