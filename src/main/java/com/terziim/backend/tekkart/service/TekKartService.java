package com.terziim.backend.tekkart.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.tekkart.dto.request.IcpTekKartRequest;

import java.util.List;

public interface TekKartService {
    IcpResponseModel<String> sendTekKart(IcpTekKartRequest request);

    IcpResponseModel<List<IcpTekKartRequest>> showAllToSellers(IcpJustJwt request, int offset);

    IcpResponseModel<IcpTekKartRequest> showSpecificToSeller(IcpJustJwt request, Long id);

    IcpResponseModel<IcpTekKartRequest> showSpecificToBuyer(IcpJustJwt request, Long id);

    IcpResponseModel<List<IcpTekKartRequest>> showAllToBuyer(IcpJustJwt request, int offset);

    IcpResponseModel<String> acceptTekKart(IcpJustJwt request);

    IcpResponseModel<String> declineTekKart(IcpJustJwt request);

    IcpResponseModel<String> rejectTekKartProcessAsSeller(IcpJustJwt request);

    IcpResponseModel<String> rejectTekKartProcessAsBuyer(IcpJustJwt request);
}
