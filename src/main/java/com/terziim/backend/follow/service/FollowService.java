package com.terziim.backend.follow.service;

import com.terziim.backend.follow.dto.IcpFollowResponse;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.request.IcpSingleData;
import com.terziim.backend.icpcommication.response.IcpResponseModel;

import java.util.List;

public interface FollowService {
    IcpResponseModel<String> followUser(IcpSingleData data);

    IcpResponseModel<String> unfollowUser(IcpSingleData data);

    IcpResponseModel<List<IcpFollowResponse>> getUserFollowers(IcpJustJwt data);

    IcpResponseModel<List<IcpFollowResponse>> getUserFollowings(IcpJustJwt data);

}
