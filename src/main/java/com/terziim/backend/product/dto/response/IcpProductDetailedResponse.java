package com.terziim.backend.product.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpProductDetailedResponse {

    private Long productId;

    private String sellerId;

    private String categoryId;
    private String subCategoryId;

    private String productName;
    private String productBrand;
    private String productInfo;
    private String productTicket;
    private String gender;

    private Float productPrice;
    private Float priceBeforeDiscount;

    private String sellerType;
    private String status;

    private Set<IcpSizeCountResponse> sizeCounts;

    private Boolean returnable;
    private Boolean changeable;
    private Boolean offeredPrice;

    //private Float productStar;
    private Integer productLikes;

    private Boolean isUserLiked;

    private Boolean isNew;


}
