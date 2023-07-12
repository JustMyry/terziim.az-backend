package com.terziim.backend.category.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IcpCategoryRequest {

    private String jwt;

    private String categoryName;
    private String about;


}
