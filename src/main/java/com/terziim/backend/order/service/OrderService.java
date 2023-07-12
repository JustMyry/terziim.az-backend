package com.terziim.backend.order.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.order.dto.request.IcpOrderRequest;
import com.terziim.backend.order.dto.response.IcpOrderResponseBUYER;
import com.terziim.backend.order.dto.response.IcpOrderResponseSELLER;

import java.util.List;

public interface OrderService {


    IcpResponseModel<IcpOrderResponseBUYER> giveAnOrder(IcpOrderRequest payload);

    IcpResponseModel<List<IcpOrderResponseBUYER>> getActiveOrdersOfBuyer(IcpJustJwt payload, int offset);

    IcpResponseModel<List<IcpOrderResponseBUYER>> getAllOrdersOfBuyer(IcpJustJwt payload, int offset);

    IcpResponseModel<IcpOrderResponseBUYER> getSpecialOrderForBuyer(IcpJustJwt payload, Long orderId);

    IcpResponseModel<String> rejectOrderFromBuyer(IcpJustJwt payload, Long orderId);

    IcpResponseModel<List<IcpOrderResponseSELLER>> getActiveOrdersOfSeller(IcpJustJwt payload, int offset);

    IcpResponseModel<List<IcpOrderResponseSELLER>> getUnrepliedOrdersOfSeller(IcpJustJwt payload, int offset);

    IcpResponseModel<List<IcpOrderResponseSELLER>> getFinishedOrdersOfSeller(IcpJustJwt payload, int offset);

    IcpResponseModel<String> rejectOrderBySeller(IcpJustJwt payload, Long offerId);

    IcpResponseModel<IcpOrderResponseSELLER> getSpecialOrderForSeller(IcpJustJwt payload, Long orderId);

    IcpResponseModel<String> acceptOrderBySeller(IcpJustJwt payload, Long orderId);

    IcpResponseModel<String> declineOrderBySeller(IcpJustJwt payload, Long orderId);
}
