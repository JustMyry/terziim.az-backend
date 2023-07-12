package com.terziim.backend.like.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.like.dto.request.IcpLikeRequest;
import com.terziim.backend.like.dto.response.IcpLikeResponse;

import java.util.List;

public interface LikeService {

    IcpResponseModel<String> unLikeProducts(IcpJustJwt payload, Long id);

    IcpResponseModel<String> likeProducts(IcpLikeRequest payload);

    IcpResponseModel<List<IcpLikeResponse>> likedProducts(IcpJustJwt payload, int offset);

}
