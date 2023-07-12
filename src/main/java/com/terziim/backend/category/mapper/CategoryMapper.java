package com.terziim.backend.category.mapper;


import com.terziim.backend.category.dto.request.IcpCategoryRequest;
import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.model.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = getMapper(CategoryMapper.class);


    ProductCategory getCategoryFromRequest(IcpCategoryRequest request);

    @Mapping(source = "categoryName", target = "categoryName")
    @Mapping(source = "about", target = "about")
    IcpCategoryResponse getCategoryResponse(ProductCategory category);


}
