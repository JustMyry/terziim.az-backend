package com.terziim.backend.category.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpSubCategoryRequest {


    private String jwt;

    private Long categoryId;
    private String subCategoryName;
    private String about;

}
