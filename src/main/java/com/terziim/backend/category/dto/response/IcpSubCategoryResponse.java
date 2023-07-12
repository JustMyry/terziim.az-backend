package com.terziim.backend.category.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpSubCategoryResponse {

    private Long id;

    private Long categoryId;
    private String subCategoryName;
    private String about;

}
