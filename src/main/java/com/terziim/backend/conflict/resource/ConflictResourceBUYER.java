package com.terziim.backend.conflict.resource;


import com.terziim.backend.conflict.dto.request.IcpConflictRequest;
import com.terziim.backend.conflict.dto.response.IcpConflictResponse;
import com.terziim.backend.conflict.service.ConflictService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anlasilmazliq/alici")
public class ConflictResourceBUYER {

    private final ConflictService service;
    public ConflictResourceBUYER(ConflictService service) {
        this.service = service;
    }


    @PostMapping("/bildir")
    public IcpResponseModel<String> buyerReportsConflict(@RequestBody IcpConflictRequest request){
        return service.buyerReportsConflict(request);
    }



    @PostMapping("/goster")
    public IcpResponseModel<List<IcpConflictResponse>> showBuyersConflicts(@RequestBody IcpJustJwt request, @RequestParam("offset") int offset){
        return service.showBuyersConflicts(request, offset);
    }


}
