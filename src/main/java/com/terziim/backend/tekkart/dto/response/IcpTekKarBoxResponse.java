package com.terziim.backend.tekkart.dto.response;

import lombok.Builder;
import lombok.Data;

@Deprecated
public class IcpTekKarBoxResponse {

    private Long id;
    private String sellerId;

    private Long productId;
    private String productBrand;
    private String productName;

}
