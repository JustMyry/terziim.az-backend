package com.terziim.backend.conflict.resource;


import com.terziim.backend.conflict.dto.request.IcpConflictRequest;
import com.terziim.backend.conflict.dto.response.IcpConflictResponse;
import com.terziim.backend.conflict.service.ConflictService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anlasilmazliq/satici")
public class ConflictResourceSELLER {

    private final ConflictService service;
    public ConflictResourceSELLER(ConflictService service) {
        this.service = service;
    }


    @PostMapping("/bildir")
    public IcpResponseModel<String> sellerReportsConflict(@RequestBody IcpConflictRequest request){
        return service.sellerReportsConflict(request);
    }



    @PostMapping("/goster")
    public IcpResponseModel<List<IcpConflictResponse>> showSellersConflicts(@RequestBody IcpJustJwt request, @RequestParam("offset") int offset){
        return service.showSellersConflicts(request, offset);
    }


}
