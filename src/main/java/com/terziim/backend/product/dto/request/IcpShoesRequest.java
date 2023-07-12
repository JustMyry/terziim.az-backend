package com.terziim.backend.product.dto.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IcpShoesRequest {

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

    private Integer countOf30;
    private Integer countOf31;
    private Integer countOf32;
    private Integer countOf33;
    private Integer countOf34;
    private Integer countOf35;
    private Integer countOf36;
    private Integer countOf37;
    private Integer countOf38;
    private Integer countOf39;
    private Integer countOf40;
    private Integer countOf41;
    private Integer countOf42;
    private Integer countOf43;
    private Integer countOf44;
    private Integer countOf45;
    private Integer countOf46;

}
