package com.terziim.backend.tekkart.mapper;


import com.terziim.backend.tekkart.dto.request.IcpTekKartRequest;
import com.terziim.backend.tekkart.dto.response.IcpTekKartResponse;
import com.terziim.backend.tekkart.model.TekKart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface TekKartMapper {

    TekKartMapper INSTANCE = getMapper(TekKartMapper.class);


    @Mapping(target = "isActive", expression = "java(true)")
    TekKart getTekKartFromRequest(IcpTekKartRequest payload);

    IcpTekKartResponse getResponseFromTekKart(TekKart tekKart);


}
