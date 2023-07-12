package com.terziim.backend.product.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mehsul/istifadeci")
public class ProductControllerUSER {

    private final ProductServiceImpl service;
    public ProductControllerUSER(ProductServiceImpl service){
        this.service = service;
    }



    @PostMapping("/yeni/istifadeci")
    public IcpResponseModel<List<IcpProductMiniResponse>> showNewProductsForUser(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.showNewProductsForUser(offset, payload);
    }


    @PostMapping("/yeni/vip/istifadeci")
    public IcpResponseModel<List<IcpProductMiniResponse>> showNewVIPProductsForUser(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.showNewVIPProductsForUser(offset, payload);
    }



    @PostMapping("/xususi/{productId}")
    public IcpResponseModel<IcpProductDetailedResponse> showSpecificProduct(@RequestBody IcpJustJwt payload, @PathVariable Long productId){
        return service.showProductForSpecificUser(productId, payload);
    }


    @PostMapping("/hesab/{accId}")
    public IcpResponseModel<List<IcpProductMiniResponse>> showProductsOfUserToUser(@RequestBody IcpJustJwt payload,
                                                                                   @PathVariable String accId, @RequestParam("offset") int offset){
        return service.showProductsOfUserToUser(accId, payload, offset);
    }


}
