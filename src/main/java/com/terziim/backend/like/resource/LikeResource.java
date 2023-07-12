package com.terziim.backend.like.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.like.dto.request.IcpLikeRequest;
import com.terziim.backend.like.dto.response.IcpLikeResponse;
import com.terziim.backend.like.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beyen")
public class LikeResource {

    private final LikeService service;
    public LikeResource(LikeService service){
        this.service = service;
    }


    @PostMapping("/pozitiv/mehsul")
    public IcpResponseModel<String> likeProduct(@RequestBody IcpLikeRequest payload){
        return service.likeProducts(payload);
    }


    @PostMapping("/negativ/mehsul")
    public IcpResponseModel<String> unLikeProduct(@RequestBody IcpJustJwt payload, @RequestParam("id") Long id){
        return service.unLikeProducts(payload, id);
    }


    @PostMapping("/mehsullarim")
    public IcpResponseModel<List<IcpLikeResponse>> likedProducts(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offet){
        return service.likedProducts(payload, offet);
    }


}
