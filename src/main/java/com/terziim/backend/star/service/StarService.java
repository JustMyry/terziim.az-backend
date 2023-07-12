package com.terziim.backend.star.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.star.dto.IcpStarRequest;

public interface StarService {
    IcpResponseModel<Float> getUsersStar(String username);

    IcpResponseModel<Float> getUsersStarDetails(IcpStarRequest payload, String username);

    IcpResponseModel<String> ulduzVer(IcpStarRequest paylaod, String username);

    IcpResponseModel<String> ulduzuGeriAl(IcpJustJwt paylaod, String username);
}
