package com.terziim.backend.community.resource;


import com.terziim.backend.community.dto.request.IcpCommunityRequest;
import com.terziim.backend.community.dto.response.IcpCommunityResponse;
import com.terziim.backend.community.service.CommunityService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topluluq")
public class CommunityResource {

    private final CommunityService service;
    public CommunityResource(CommunityService service){
        this.service = service;
    }


    @PostMapping("/elaveet")
    public IcpResponseModel<String> addCommunityMessage(@RequestBody IcpCommunityRequest payload) {
        return service.addCommunityMessage(payload);
    }


    @PostMapping("/sil")
    public IcpResponseModel<String> deleteCommunityMessage(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id) {
        return service.deleteCommunityMessage(payload, id);
    }


    @GetMapping("/gor")
    public IcpResponseModel<List<IcpCommunityResponse>> showUserCommunityMessages(@RequestParam("user")String userId) {
        return service.showUserCommunityMessages(userId);
    }

}
