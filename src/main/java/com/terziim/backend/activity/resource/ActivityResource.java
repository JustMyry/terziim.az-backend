package com.terziim.backend.activity.resource;


import com.terziim.backend.activity.dto.response.IcpActivityResponse;
import com.terziim.backend.activity.service.ActivityService;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityResource {

    private final ActivityService service;
    public ActivityResource(ActivityService service) {
        this.service = service;
    }



    @GetMapping("/show/all")
    IcpResponseModel<List<IcpActivityResponse>> showAllActions(@RequestParam("offset") int offset){
        return service.showAllActions(offset);
    }


    @GetMapping("/show/active")
    IcpResponseModel<List<IcpActivityResponse>> showAllActiveActions(@RequestParam("offset") int offset) {
        return service.showAllActiveActions(offset);
    }


    @GetMapping("/show/specific/id/{id}")
    IcpResponseModel<IcpActivityResponse> showSpecificActionWithId(@PathVariable Long id){
        return service.showSpecificActionWithId(id);
    }


    @GetMapping("/show/specific/userid/{name}")
    IcpResponseModel<IcpActivityResponse> showSpecificActionWithUsername(@PathVariable String name){
        return service.showSpecificActionWithUsername(name);
    }


//    @GetMapping("/delete/{id}")
//    IcpResponseModel<String> deleteActionById(@PathVariable Long id){
//        return service.deleteActionById(id);
//    }


    @GetMapping("/set/deactive/{id}")
    IcpResponseModel<String> setActionDeactive(@PathVariable Long id){
        return service.setActionDeactive(id);
    }


    @GetMapping("/set/active/{id}")
    IcpResponseModel<String> setActionActive(@PathVariable Long id){
        return service.setActionActive(id);
    }

}
