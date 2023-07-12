package com.terziim.backend.offer.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;

import java.util.List;

public interface OfferService {
    IcpResponseModel<List<IcpProductMiniResponse>> showOffersToUser(IcpJustJwt payload);

    IcpResponseModel<List<IcpProductMiniResponse>> showAllOffersToUser(IcpJustJwt payload);
}
