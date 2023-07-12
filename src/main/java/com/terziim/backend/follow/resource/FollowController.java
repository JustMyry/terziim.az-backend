package com.terziim.backend.follow.resource;

import com.terziim.backend.follow.dto.IcpFollowResponse;
import com.terziim.backend.follow.service.impl.FollowServiceImpl;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.request.IcpSingleData;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowServiceImpl service;
    public FollowController(FollowServiceImpl service) {
        this.service = service;
    }


    @PostMapping("/to")
    public IcpResponseModel<String> followUser(@RequestBody IcpSingleData data){
        return service.followUser(data);
    }


    @PostMapping("/unf")
    public IcpResponseModel<String> unfollowUser(@RequestBody IcpSingleData data){
        return service.unfollowUser(data);
    }

    @PostMapping("/mine/followers")
    public IcpResponseModel<List<IcpFollowResponse>> getUserFollowers(@RequestBody IcpJustJwt data){
        return service.getUserFollowers(data);
    }

    @PostMapping("/mine/followings")
    public IcpResponseModel<List<IcpFollowResponse>> getUserFollowings(@RequestBody IcpJustJwt data){
        return service.getUserFollowings(data);
    }
}
