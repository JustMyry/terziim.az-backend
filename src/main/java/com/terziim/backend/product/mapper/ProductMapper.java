package com.terziim.backend.product.mapper;


import com.terziim.backend.product.dto.request.IcpProductRequest;
import com.terziim.backend.product.dto.request.IcpShoesRequest;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.dto.request.IcpClothesRequest;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.core.parameters.P;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = getMapper(ProductMapper.class);


    @Mapping(target = "checked", expression = "java(false)")
    @Mapping(target = "cash", expression = "java(true)")
    @Mapping(target = "terziimPay", expression = "java(false)")
    @Mapping(source = "isNew", target = "isNew")
    Product getClothesProduct(IcpClothesRequest request);


    @Mapping(target = "checked", expression = "java(false)")
    @Mapping(target = "cash", expression = "java(true)")
    @Mapping(target = "terziimPay", expression = "java(false)")
    @Mapping(source = "isNew", target = "isNew")
    Product getShoesProduct(IcpShoesRequest request);


    @Mapping(target = "checked", expression = "java(false)")
    @Mapping(target = "cash", expression = "java(true)")
    @Mapping(target = "terziimPay", expression = "java(false)")
    @Mapping(source = "isNew", target = "isNew")
    Product getOtherProduct(IcpProductRequest request);


    @Mapping(source = "new", target = "isNew")
    IcpProductMiniResponse getProductMiniResponse (Product product);


    @Mapping(source = "new", target = "isNew")
    IcpProductDetailedResponse getProductDetailedResponse (Product product);

}

