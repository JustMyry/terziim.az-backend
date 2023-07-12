package com.terziim.backend.conflict.service;

import com.terziim.backend.conflict.dto.request.IcpConflictRequest;
import com.terziim.backend.conflict.dto.response.IcpConflictResponse;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;

import java.util.List;

public interface ConflictService {
    IcpResponseModel<String> sellerReportsConflict(IcpConflictRequest request);

    IcpResponseModel<List<IcpConflictResponse>> showSellersConflicts(IcpJustJwt request, int offset);

    IcpResponseModel<String> buyerReportsConflict(IcpConflictRequest request);

    IcpResponseModel<List<IcpConflictResponse>> showBuyersConflicts(IcpJustJwt request, int offset);
}
