package com.terziim.backend.conflict.dto.request;



import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class IcpConflictRequest {


    private String jwt;

    private Integer categoryId;

    private String orderId;

    private String description;

}
