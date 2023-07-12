package com.terziim.backend.community.service.impl;


import com.terziim.backend.community.dto.request.IcpCommunityRequest;
import com.terziim.backend.community.dto.response.IcpCommunityResponse;
import com.terziim.backend.community.mapper.CommunityMapper;
import com.terziim.backend.community.model.Community;
import com.terziim.backend.community.repository.CommunityRepository;
import com.terziim.backend.community.service.CommunityService;
import com.terziim.backend.guard.service.GuardExternalService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository repository;
    private final GuardExternalService guard;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    public CommunityServiceImpl(CommunityRepository repository, GuardExternalService guard, UserExternalService userService, JwtProvider jwtProvider) {
        this.repository = repository;
        this.guard = guard;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }



    @Override
    public IcpResponseModel<String> addCommunityMessage(IcpCommunityRequest payload) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        List<Community> test = repository.findCommunitiesByAuthorId(user.getUserId());
        if(user==null || !user.isNotLocked() || guard.verifyRequestFromUnUsualWords(payload.getCommunity(), " ") || test.size()>=3 || !user.getUserType().equals("SELLER"))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Community community = CommunityMapper.INSTANCE.getCommunityEntity(payload);
        community.setAuthorId(user.getUserId());
        community.setActive(true);
        repository.save(community);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> deleteCommunityMessage(IcpJustJwt payload, Long id) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Community community = repository.findCommunityById(id);
        if(user==null || !user.isNotLocked() || community == null || !community.getAuthorId().equals(user.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        community.setActive(false);
        repository.save(community);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpCommunityResponse>> showUserCommunityMessages(String userId) {
        AppUser user = userService.findAppUserByUserId(userId);
        if(user==null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpCommunityResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<List<IcpCommunityResponse>>builder()
                .response(repository.findCommunitiesByAuthorId(userId).stream().map(CommunityMapper.INSTANCE::getCommunityResponse).collect(Collectors.toList()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



}
