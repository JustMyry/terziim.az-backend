package com.terziim.backend.search.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.search.service.SearchService;
import com.terziim.backend.user.dto.response.IcpUserResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/axtar")
public class SearchController {

    private final SearchService service;
    public SearchController(SearchService service){
        this.service = service;
    }


    @GetMapping("/ad")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameGET(@RequestParam("ad") String ad, @RequestParam("offset") int offset){
        return service.searchOnNameGET(ad, offset);
    }


    @PostMapping("/ad")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNamePOST(@RequestBody IcpJustJwt payload, @RequestParam("ad") String ad,
                                                                           @RequestParam("offset") int offset){
        return service.searchOnNamePOST(payload, ad, offset);
    }


    @GetMapping("/brend")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnBrandGET(@RequestParam("brend") String brend, @RequestParam("offset") int offset){
        return service.searchOnBrandGET(brend, offset);
    }


    @PostMapping("/brend")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnBrandPOST(@RequestBody IcpJustJwt payload, @RequestParam("brend") String brend,
                                                                            @RequestParam("offset") int offset){
        return service.searchOnBrandPOST(payload, brend, offset);
    }


    @GetMapping("/satici")
    public IcpResponseModel<IcpUserResponse> searchOnSellerGET(@RequestParam("ad") String ad){
        return service.searchOnSellerGET(ad);
    }


    @PostMapping("/satici")
    public IcpResponseModel<IcpUserResponse> searchOnSellerPOST(@RequestBody IcpJustJwt payload, @RequestParam("ad") String ad){
        return service.searchOnSellerPOST(payload, ad);
    }


    @GetMapping("/kategoriya")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryGET(@RequestParam("id") Long id, @RequestParam("offset") int offset){
        return service.searchOnCategoryGET(id, offset);
    }


    @PostMapping("/kategoriya")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryPOST(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id,
                                                                               @RequestParam("offset") int offset){
        return service.searchOnCategoryPOST(payload, id, offset);
    }


    @GetMapping("/altkategoriya")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnSubCategoryGET(@RequestParam("id") Long id, @RequestParam("offset") int offset){
        return service.searchOnSubCategoryGET(id, offset);
    }


    @PostMapping("/altkategoriya")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnSubCategoryPOST(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id,
                                                                                  @RequestParam("offset") int offset){
        return service.searchOnSubCategoryPOST(payload, id, offset);
    }


    @GetMapping("/advekategoriya")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndCategoryGET(@RequestParam("ad") String ad, @RequestParam("id") Long id, @RequestParam("offset") int offset){
        return service.searchOnNameAndCategoryGET(ad, id, offset);
    }


    @PostMapping("/advekategoriya")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndCategoryPOST(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id,
                                                                                      @RequestParam("ad") String ad, @RequestParam("offset") int offset){
        return service.searchOnNameAndCategoryPOST(ad, payload, id, offset);
    }


    @GetMapping("/cinsiyyet")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchGenderGET(@RequestParam("gender") String gender,
                                                                                   @RequestParam("offset") int offset){
        return service.searchGenderGET(gender, offset);
    }


    @PostMapping("/cinsiyyet")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchGenderGETPOST(@RequestBody IcpJustJwt payload,
                                                                                    @RequestParam("gender") String gender, @RequestParam("offset") int offset){
        return service.searchGenderPOST(payload, gender, offset);
    }


    @GetMapping("/advecinsiyyet")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndGenderGET(@RequestParam("ad") String ad, @RequestParam("gender") String gender,
                                                                                   @RequestParam("offset") int offset){
        return service.searchOnNameAndGenderGET(ad, gender, offset);
    }


    @PostMapping("/advecinsiyyet")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndGenderPOST(@RequestBody IcpJustJwt payload, @RequestParam("ad") String ad,
                                                                                    @RequestParam("gender") String gender, @RequestParam("offset") int offset){
        return service.searchOnNameAndGenderPOST(ad, payload, gender, offset);
    }


    @GetMapping("/detalli")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryAndSubCategoryAndGenderGET(@RequestParam("katId") Long katId, @RequestParam("skatId") Long skatId,
                                                                                                     @RequestParam("gender") String gender, @RequestParam("offset") int offset){
        return service.searchOnCategoryAndSubCategoryAndGenderGET(katId, skatId, gender, offset);
    }


    @PostMapping("/detalli")
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryAndSubCategoryAndGenderPOST(@RequestBody IcpJustJwt payload, @RequestParam("katId") Long katId,
                                                                                                      @RequestParam("skatId") Long skatId, @RequestParam("gender") String gender,
                                                                                                      @RequestParam("offset") int offset){
        return service.searchOnCategoryAndSubCategoryAndGenderPOST(katId, skatId, gender, offset, payload);
    }


}
