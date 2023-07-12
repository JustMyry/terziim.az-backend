package com.terziim.backend.star.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.star.dto.IcpStarRequest;
import com.terziim.backend.star.service.StarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ulduz")
public class StarController {

    private final StarService service;
    public StarController(StarService service){
        this.service = service;
    }



    @GetMapping("/istifadeci")
    public IcpResponseModel<Float> getUsersStar(@RequestParam("ad") String username){
        return service.getUsersStar(username);
    }


    @PostMapping("/istifadeci/detalli")
    public IcpResponseModel<Float> getUsersStarDetails(@RequestBody IcpStarRequest payload, @RequestParam("ad") String username){
        return service.getUsersStarDetails(payload, username);
    }


    @PostMapping("/ver")
    public IcpResponseModel<String> ulduzVer(@RequestBody IcpStarRequest paylaod, @RequestParam("ad") String username){
        return service.ulduzVer(paylaod, username);
    }


    @PostMapping("/gerial")
    public IcpResponseModel<String> ulduzuGeriAl(@RequestBody IcpJustJwt paylaod, @RequestParam("ad") String username){
        return service.ulduzuGeriAl(paylaod, username);
    }



}
