package com.terziim.backend.category.mapper;

import com.terziim.backend.category.dto.request.IcpSubCategoryRequest;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.category.model.ProductSubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {

    SubCategoryMapper INSTANCE = getMapper(SubCategoryMapper.class);


    ProductSubCategory getSubCategoryFromRequest(IcpSubCategoryRequest request);


    @Mapping(source = "subCategoryName", target = "subCategoryName")
    @Mapping(source = "categoryId", target = "categoryId")
    @Mapping(source = "about", target = "about")
    IcpSubCategoryResponse getSubCategoryResponse(ProductSubCategory category);



}
