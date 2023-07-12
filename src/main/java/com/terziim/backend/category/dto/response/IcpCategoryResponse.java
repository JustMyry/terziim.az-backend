package com.terziim.backend.category.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpCategoryResponse {

    private Long id;

    private String categoryName;
    private String about;


}
