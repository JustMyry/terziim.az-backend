package com.terziim.backend.order.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.order.dto.request.IcpOrderRequest;
import com.terziim.backend.order.dto.response.IcpOrderResponseBUYER;
import com.terziim.backend.order.dto.response.IcpOrderResponseSELLER;
import com.terziim.backend.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sifarisler/alici")
public class OrderResourceBUYER {


    private final OrderService service;
    public OrderResourceBUYER(OrderService service){
        this.service = service;
    }


    @PostMapping("/ver")
    public IcpResponseModel<IcpOrderResponseBUYER> giveAnOrder(@RequestBody IcpOrderRequest payload){
        return service.giveAnOrder(payload);
    }


    @PostMapping("/aktiv")
    public IcpResponseModel<List<IcpOrderResponseBUYER>> getActiveOrdersOfBuyer(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.getActiveOrdersOfBuyer(payload, offset);
    }


    @PostMapping("/butun")
    public IcpResponseModel<List<IcpOrderResponseBUYER>> getAllOrdersOfBuyer(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.getAllOrdersOfBuyer(payload, offset);
    }


    @PostMapping("/xususi/{orderId}")
    public IcpResponseModel<IcpOrderResponseBUYER> getSpecialOrderForBuyer(@RequestBody IcpJustJwt payload, @PathVariable Long orderId){
        return service.getSpecialOrderForBuyer(payload, orderId);
    }


    @PostMapping("/legvet")
    public IcpResponseModel<String> rejectOrderFromBuyer(@RequestBody IcpJustJwt payload, @RequestParam("offerId") Long offerId){
        return service.rejectOrderFromBuyer(payload, offerId);
    }

}
