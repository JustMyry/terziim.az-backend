package com.terziim.backend.requisition.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.requisition.dto.request.IcpRequisitionRequest;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.requisition.dto.request.IcpBecomeSellerRequest;

import java.util.List;

public interface RequisitionService {
    IcpResponseModel<String> createRequisition(IcpRequisitionRequest payload);

    IcpResponseModel<List<IcpRequisitionResponse>> getUsersRequisitions(IcpJustJwt payload);

    IcpResponseModel<String> becomeSeller(IcpBecomeSellerRequest payload);
}
