package com.terziim.backend.requisition.mapper;

import com.terziim.backend.requisition.dto.response.IcpBecomeRequisitonResponse;
import com.terziim.backend.requisition.model.BecomeRequisition;
import com.terziim.backend.requisition.dto.request.IcpBecomeSellerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper(componentModel = "spring")
public interface BecomeMapper {

    BecomeMapper INSTANCE = getMapper(BecomeMapper.class);


    @Mapping(target = "category", expression = "java(3)")
    BecomeRequisition getBecomeSellerFromRequest(IcpBecomeSellerRequest request);



    @Mapping(source = "address", target = "address")
    IcpBecomeRequisitonResponse getBecomeReqResponse(BecomeRequisition requisition);

}
