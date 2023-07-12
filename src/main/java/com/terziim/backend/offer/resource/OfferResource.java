package com.terziim.backend.offer.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.offer.service.OfferService;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teklifler")
public class OfferResource {

    private final OfferService service;
    public OfferResource(OfferService service) {
        this.service = service;
    }

    @PostMapping("/goster")
    public IcpResponseModel<List<IcpProductMiniResponse>> showOffersToUser(@RequestBody IcpJustJwt payload){
        return service.showOffersToUser(payload);
    }


    @PostMapping("/goster/butun")
    public IcpResponseModel<List<IcpProductMiniResponse>> showAllOffersToUser(@RequestBody IcpJustJwt payload){
        return service.showAllOffersToUser(payload);
    }

}
