package com.terziim.backend.follow.service.impl;

import com.terziim.backend.follow.dto.IcpFollowResponse;
import com.terziim.backend.follow.mapper.FollowMapper;
import com.terziim.backend.follow.model.FollowModel;
import com.terziim.backend.follow.repository.FollowRepository;
import com.terziim.backend.follow.service.FollowService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.request.IcpSingleData;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.impl.UserExternalServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FollowServiceImpl implements FollowService {

    private final FollowRepository repository;
    private final UserExternalServiceImpl userService;
    private final JwtProvider jwtProvider;
    public FollowServiceImpl(FollowRepository repository, UserExternalServiceImpl userService, JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public IcpResponseModel<String> followUser(IcpSingleData data) {
        AppUser follower = userService.findAppUserByUserId(jwtProvider.getSubject(data.getJwt()));
        AppUser following = userService.findAppUserByUserId(data.getData());
        FollowModel test = repository.findFollowTableByFollowerAndFollowing(follower.getUserId(), following.getUserId());
        if(following == null || follower == null || test!=null || !follower.isNotLocked() || !following.isNotLocked())
            return IcpResponseModel.<String>builder()
                    .response("Invalid Request")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        FollowModel followModel = FollowModel.builder()
                .followerId(follower.getUserId())
                .followingId(following.getUserId())
                .isActive(true)
                .build();
        repository.save(followModel);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> unfollowUser(IcpSingleData data) {
        AppUser follower = userService.findAppUserByUserId(jwtProvider.getSubject(data.getJwt()));
        AppUser following = userService.findAppUserByUserId(data.getData());
        FollowModel followModel = repository.findFollowTableByFollowerAndFollowing(follower.getUserId(), following.getUserId());
        if(following == null || follower == null || followModel==null || !follower.isNotLocked() || !following.isNotLocked())
            return IcpResponseModel.<String>builder()
                    .response("Invalid Request")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        repository.delete(followModel);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpFollowResponse>> getUserFollowers(IcpJustJwt data) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(data.getJwt()));
        if(user == null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpFollowResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<IcpFollowResponse> followResponses = repository.findFollowModelsByFollowingId(user.getUserId()).stream().map(s->{
           return FollowMapper.INSTANCE.getFollowResponseFromUser(userService.findAppUserByUserId(s.getFollowerId()));
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpFollowResponse>>builder()
                .response(followResponses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpFollowResponse>> getUserFollowings(IcpJustJwt data) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(data.getJwt()));
        if(user == null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpFollowResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<IcpFollowResponse> followResponses = repository.findFollowModelsByFollowerId(user.getUserId()).stream().map(s->{
            return FollowMapper.INSTANCE.getFollowResponseFromUser(userService.findAppUserByUserId(s.getFollowingId()));
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpFollowResponse>>builder()
                .response(followResponses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }
}
