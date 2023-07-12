package com.terziim.backend.product.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.request.IcpSingleData;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.dto.request.IcpClothesRequest;
import com.terziim.backend.product.dto.request.IcpProductRequest;
import com.terziim.backend.product.dto.request.IcpShoesRequest;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mehsul/satici")
public class ProductControllerSELLER {

    private final ProductServiceImpl service;
    public ProductControllerSELLER(ProductServiceImpl service){
        this.service = service;
    }



    @PostMapping("/elaveet/paltar")
    public IcpResponseModel<IcpProductDetailedResponse> addClothesProduct(@RequestBody IcpClothesRequest payload){
        return service.addClothesProduct(payload);
    }

    @PostMapping("/elaveet/ayaqqabi")
    public IcpResponseModel<IcpProductDetailedResponse> addShoesProduct(@RequestBody IcpShoesRequest payload){
        return service.addShoesProduct(payload);
    }

    @PostMapping("/elaveet/diger")
    public IcpResponseModel<IcpProductDetailedResponse> addOtherProduct(@RequestBody IcpProductRequest payload){
        return service.addOtherProduct(payload);
    }


    @PostMapping("/sil")
    public IcpResponseModel<String> deleteProduct(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id){
        return service.deleteProduct(payload, id);
    }


    @PostMapping("/deyis/status/mehsul/satildi")
    public IcpResponseModel<String> changeStatusOfProductToSold(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id){
        return service.changeStatusOfProductToSold(payload, id);
    }


    @PostMapping("/deyis/status/mehsul/elde")
    public IcpResponseModel<String> changeStatusOfProductToExists(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id){
        return service.changeStatusOfProductToExists(payload, id);
    }


    @PostMapping("/deyis/status/size/{productId}/{size}")
    public IcpResponseModel<String> changeCountOfSizeCount(@RequestBody IcpJustJwt payload, @RequestParam("say") Integer say, @PathVariable String size,
                                                                    @PathVariable Long productId){
        return service.changeCountOfSizeCount(payload, say, size, productId);
    }


    @PostMapping("/endirim/{productId}")
    public IcpResponseModel<IcpProductDetailedResponse> makeDiscount(@RequestBody IcpSingleData payload, @PathVariable Long productId){
        return service.makeDiscount(productId, payload);
    }



}
