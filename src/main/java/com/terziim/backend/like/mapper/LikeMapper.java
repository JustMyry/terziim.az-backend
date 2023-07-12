package com.terziim.backend.like.mapper;

import com.terziim.backend.like.dto.response.IcpLikeResponse;
import com.terziim.backend.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;



@Mapper(componentModel = "spring")
public interface LikeMapper {

    LikeMapper INSTANCE = getMapper(LikeMapper.class);

    @Mapping(source = "id", target = "productId")
    IcpLikeResponse getLikeResponse(Product product);

}
