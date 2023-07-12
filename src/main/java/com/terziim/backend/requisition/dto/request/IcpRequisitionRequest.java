package com.terziim.backend.requisition.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpRequisitionRequest {

    private String jwt;

    private int requisitionCategoryId;
    private String requisitionHeader;
    private String requisitionText;


}
