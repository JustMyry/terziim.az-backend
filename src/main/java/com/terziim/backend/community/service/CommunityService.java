package com.terziim.backend.community.service;

import com.terziim.backend.community.dto.request.IcpCommunityRequest;
import com.terziim.backend.community.dto.response.IcpCommunityResponse;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;

import java.util.List;

public interface CommunityService {
    IcpResponseModel<String> addCommunityMessage(IcpCommunityRequest payload);

    IcpResponseModel<String> deleteCommunityMessage(IcpJustJwt payload, Long id);

    IcpResponseModel<List<IcpCommunityResponse>> showUserCommunityMessages(String userId);
}
