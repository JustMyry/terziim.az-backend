package com.terziim.backend.order.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.order.dto.response.IcpOrderResponseSELLER;
import com.terziim.backend.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sifarisler/satici")
public class OrderResourceSELLER {


    private final OrderService service;
    public OrderResourceSELLER(OrderService service){
        this.service = service;
    }



    @PostMapping("/gozleyen")
    public IcpResponseModel<List<IcpOrderResponseSELLER>> getActiveOrdersOfSeller(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.getActiveOrdersOfSeller(payload, offset);
    }


    @PostMapping("/islemde")
    public IcpResponseModel<List<IcpOrderResponseSELLER>> getProcessingOrdersOfSeller(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.getUnrepliedOrdersOfSeller(payload, offset);
    }


    @PostMapping("/bitmis")
    public IcpResponseModel<List<IcpOrderResponseSELLER>> getFinishedOrdersOfSeller(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.getFinishedOrdersOfSeller(payload, offset);
    }


    @PostMapping("/legvet")
    public IcpResponseModel<String> rejectOrderBySeller(@RequestBody IcpJustJwt payload, @RequestParam("offerId") Long offerId){
        return service.rejectOrderBySeller(payload, offerId);
    }


    @PostMapping("/xususi/{orderId}")
    public IcpResponseModel<IcpOrderResponseSELLER> getSpecialOrderForSeller(@RequestBody IcpJustJwt payload, @PathVariable Long orderId){
        return service.getSpecialOrderForSeller(payload, orderId);
    }


    @PostMapping("/tesdiq/{orderId}")
    public IcpResponseModel<String> acceptOrderBySeller(@RequestBody IcpJustJwt payload, @PathVariable Long orderId){
        return service.acceptOrderBySeller(payload, orderId);
    }


    @PostMapping("/inkar/{orderId}")
    public IcpResponseModel<String> declineOrderBySeller(@RequestBody IcpJustJwt payload, @PathVariable Long orderId){
        return service.declineOrderBySeller(payload, orderId);
    }

}
