package com.terziim.backend.requisition.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class IcpBecomeRequisitonResponse {

    private String senderId;

    private int category;

    private String username;
    private String email;
    private String secondPhone;

    private String address;
    private String openingTime;
    private String closingTime;

    private String description;

    private LocalDate createdAt;

    private Boolean isActive;

}
