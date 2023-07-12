package com.terziim.backend.product.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpProductRequest {

    private String jwt;

    private Long categoryId;
    private Long subCategoryId;

    private String productName;
    private String productBrand;
    private String productInfo;
    private String productTicket;
    private String gender;

    private Float productPrice;

    private Boolean isNew;

    private Boolean returnable;
    private Boolean changeable;
    private Boolean offeredPrice;

}
