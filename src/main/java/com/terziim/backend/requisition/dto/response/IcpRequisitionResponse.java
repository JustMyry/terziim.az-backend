package com.terziim.backend.requisition.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpRequisitionResponse {

    private Long id;

    private int requisitionCategoryId;

    private String senderId;
    private String requisitionHeader;
    private String requisitionText;

    private LocalDate createdAt;
    private LocalDate updatedAt;


}
