package com.terziim.backend.requisition.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.requisition.dto.request.IcpRequisitionRequest;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.requisition.service.RequisitionService;
import com.terziim.backend.requisition.dto.request.IcpBecomeSellerRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teleb")
public class RequisitionController {

    private final RequisitionService service;
    public RequisitionController(RequisitionService service){
        this.service = service;
    }


    @PostMapping("/gonder")
    public IcpResponseModel<String> createFeatureRequisition(@RequestBody IcpRequisitionRequest payload){
        return service.createRequisition(payload);
    }


    @PostMapping("/gonderdiklerim")
    public IcpResponseModel<List<IcpRequisitionResponse>> getUsersRequisitions(@RequestBody IcpJustJwt payload){
        return service.getUsersRequisitions(payload);
    }

    @PostMapping("/{username}/ol/satici")
    public IcpResponseModel<String> becomeSellerEndPoint(@PathVariable String username, @RequestBody IcpBecomeSellerRequest payload){
        return service.becomeSeller(payload);
    }


}
