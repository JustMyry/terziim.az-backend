package com.terziim.backend.conflict.mapper;

import com.terziim.backend.conflict.dto.request.IcpConflictRequest;
import com.terziim.backend.conflict.dto.response.IcpConflictResponse;
import com.terziim.backend.conflict.model.Conflict;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;



@Mapper(componentModel = "spring")
public interface ConflictMapper {

    ConflictMapper INSTANCE = getMapper(ConflictMapper.class);


    @Mapping(target = "userType", expression = "java(\"SELLER\")")
    @Mapping(target = "isActive", expression = "java(true)")
    Conflict getConflictFromSeller(IcpConflictRequest payload);


    @Mapping(target = "userType", expression = "java(\"BUYER\")")
    @Mapping(target = "isActive", expression = "java(true)")
    Conflict getConflictFromBuyer(IcpConflictRequest payload);


    IcpConflictResponse getConflictResponse(Conflict payload);


}
