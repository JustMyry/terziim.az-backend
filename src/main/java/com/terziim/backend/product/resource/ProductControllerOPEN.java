package com.terziim.backend.product.resource;


import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mehsul/aciq")
public class ProductControllerOPEN {

    private final ProductService service;
    public ProductControllerOPEN(ProductService service){
        this.service = service;
    }



    @GetMapping("/yeni")
    public IcpResponseModel<List<IcpProductMiniResponse>> showNewProductsForGuest(@RequestParam("offset") int offset){
        return service.showNewProductsForGuest(offset);
    }


    @GetMapping("/yeni/vip")
    public IcpResponseModel<List<IcpProductMiniResponse>> showNewVIPProductsForGuest(@RequestParam("offset") int offset){
        return service.showNewVIPProductsForGuest(offset);
    }


    @GetMapping("/xususi/{productId}")
    public IcpResponseModel<IcpProductDetailedResponse> showProductForGuest(@PathVariable Long productId){
        return service.showProductForGuest(productId);
    }


    @GetMapping("/hesab/{username}")
    public IcpResponseModel<List<IcpProductMiniResponse>> showProductsOfUserToGuest(@PathVariable String username, @RequestParam("offset") int offset){
        return service.showProductsOfUserToGuest(username, offset);
    }


    @GetMapping("/benzer/{subCatId}")
    public IcpResponseModel<List<IcpProductMiniResponse>> showSimilarProducts(@PathVariable Long subCatId, @RequestParam("offset") int offset){
        return service.showSimilarProducts(subCatId, offset);
    }



}
