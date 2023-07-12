package com.terziim.backend.conflict.dto.response;


import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IcpConflictResponse {

    private Long id;

    private String complainant;

    private Integer categoryId;

    private String orderId;

    private String userType;
    private String description;

    private String answer;

    private Boolean solved;
    private Boolean isActive;

}
