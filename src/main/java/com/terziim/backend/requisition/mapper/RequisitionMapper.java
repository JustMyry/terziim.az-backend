package com.terziim.backend.requisition.mapper;



import com.terziim.backend.requisition.dto.request.IcpRequisitionRequest;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.requisition.model.Requisition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface RequisitionMapper {



    RequisitionMapper INSTANCE = getMapper(RequisitionMapper.class);


    @Mapping(target = "isActive", expression = "java(true)")
    Requisition getRequisitionFromRequest(IcpRequisitionRequest request);


    IcpRequisitionResponse getRequisitionResponse(Requisition requisition);


}
