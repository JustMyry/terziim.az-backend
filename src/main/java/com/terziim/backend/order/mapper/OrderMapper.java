package com.terziim.backend.order.mapper;


import com.terziim.backend.order.dto.request.IcpOrderRequest;
import com.terziim.backend.order.dto.response.IcpOrderResponseADMIN;
import com.terziim.backend.order.dto.response.IcpOrderResponseBUYER;
import com.terziim.backend.order.dto.response.IcpOrderResponseSELLER;
import com.terziim.backend.order.model.Orders;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = getMapper(OrderMapper.class);

    // Saytin Idare Qrupuna geden cavablar
    @Mapping(source = "id", target = "orderId")
    IcpOrderResponseADMIN getOrderResponseAdmin(Orders orders);


    // Istifadecilere geden cavablar
    @Mapping(source = "id", target = "orderId")
    IcpOrderResponseSELLER getOrderResponseSeller(Orders orders);

    @Mapping(source = "id", target = "orderId")
    IcpOrderResponseBUYER getOrderResponseBuyer(Orders orders);

    @Mapping(target = "acceptedBySeller", expression = "java(false)")
    @Mapping(target = "seenBySeller", expression = "java(false)")
    @Mapping(target = "status", expression = "java(\"wait\")")
    @Mapping(target = "cargoPrice", expression = "java(null)")
    @Mapping(target = "isActive", expression = "java(true)")
    Orders getOrderFromRequest(IcpOrderRequest request);


    @Mapping(ignore = true, target = "id")
    void updateOrder(@MappingTarget Orders orders, IcpOrderRequest payload);



}
