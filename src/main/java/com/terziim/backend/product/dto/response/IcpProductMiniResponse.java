package com.terziim.backend.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpProductMiniResponse {

    private Long productId;

    private String sellerId;
    private String sellerName;
    private String sellerType;
    private Float sellerLikes;
    private Integer sellerLikesCount;

    private String productName;
    private String productBrand;
    private String productInfo;
    private String gender;

    private Float productPrice;
    private Float priceBeforeDiscount;

    private String status;

    private Boolean returnable;
    private Boolean changeable;
    private Integer productLikes;

    private Boolean isUserLiked;

    private Boolean isNew;

    private Boolean terziimPay;
    private Boolean cash;
}
