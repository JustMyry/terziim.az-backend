package com.terziim.backend.tekkart.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.tekkart.dto.request.IcpTekKartRequest;
import com.terziim.backend.tekkart.service.TekKartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teklif")
public class TekKartResource {

    private final TekKartService service;
    public TekKartResource(TekKartService service) {
        this.service = service;
    }



    @PostMapping("/alici/gonder")
    public IcpResponseModel<String> sendTekKart(@RequestBody IcpTekKartRequest request) {
        return service.sendTekKart(request);
    }


    @PostMapping("/satici/gelen")
    public IcpResponseModel<List<IcpTekKartRequest>> showAllToSeller(@RequestBody IcpJustJwt request, @RequestParam("offset") int offset) {
        return service.showAllToSellers(request, offset);
    }


    @PostMapping("/satici/xususi/{id}")
    public IcpResponseModel<IcpTekKartRequest> showSpecificToSeller(@RequestBody IcpJustJwt request, @PathVariable Long id) {
        return service.showSpecificToSeller(request, id);
    }


    @PostMapping("/alici/xususi/{id}")
    public IcpResponseModel<IcpTekKartRequest> showSpecificToBuyer(@RequestBody IcpJustJwt request, @PathVariable Long id) {
        return service.showSpecificToBuyer(request, id);
    }


    @PostMapping("/alici/verdiyim")
    public IcpResponseModel<List<IcpTekKartRequest>> showAllToBuyer(@RequestBody IcpJustJwt request, @RequestParam("offset") int offset) {
        return service.showAllToBuyer(request, offset);
    }


    @PostMapping("/satici/qebul")
    public IcpResponseModel<String> acceptTekKart(@RequestBody IcpJustJwt request) {
        return service.acceptTekKart(request);
    }


    @PostMapping("/satici/redd")
    public IcpResponseModel<String> declineTekKart(@RequestBody IcpJustJwt request) {
        return service.declineTekKart(request);
    }


    @PostMapping("/satici/legv")
    public IcpResponseModel<String> rejectTekKartProcessAsSeller(@RequestBody IcpJustJwt request) {
        return service.rejectTekKartProcessAsSeller(request);
    }


    @PostMapping("/alici/legv")
    public IcpResponseModel<String> rejectTekKartProcessAsBuyer(@RequestBody IcpJustJwt request) {
        return service.rejectTekKartProcessAsBuyer(request);
    }

}
