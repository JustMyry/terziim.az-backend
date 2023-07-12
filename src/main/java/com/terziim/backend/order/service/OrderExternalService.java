package com.terziim.backend.order.service;

import com.terziim.backend.order.dto.request.IcpOrderRequest;
import com.terziim.backend.order.dto.response.IcpOrderResponseADMIN;
import com.terziim.backend.order.model.Orders;

import java.time.LocalDate;
import java.util.List;

public interface OrderExternalService {


    void updateOrder(IcpOrderRequest payload, Long id);

    void changeStatusToEXAM(Long orderId);
    void changeStatusToCARGO(Long orderId);
    void changeStatusToFINISHED(Long orderId);

    void changeActivityToParam(Boolean activity, Long orderId);

    Orders getOrderById(Long id);

    IcpOrderResponseADMIN getOrderResponse(Long id);

    List<Orders> getOrdersByBuyerIdAndProductIdWithOffset(String buyerId, Long productId, int offset, int limit);

    List<IcpOrderResponseADMIN> getOrdersBySellerIdWithOffset(String sellerId, int offset, int limit);

    List<IcpOrderResponseADMIN> getOrdersByBuyerIdWithOffset(String buyerId, int offset, int limit);

    List<IcpOrderResponseADMIN> getByStatusEXSAMWithOffset(int offset, int limit);

    List<IcpOrderResponseADMIN> getByStatusWAITWithOffset(int offset, int limit);

    List<IcpOrderResponseADMIN> getByStatusCARGOWithOffset(int offset, int limit);

    List<IcpOrderResponseADMIN> getByStatusCFSandSellerId(String sellerId);

    List<IcpOrderResponseADMIN> getByStatusCFBandBuyerId(String BuyerId);
            ;
    List<IcpOrderResponseADMIN> getByOfferId(Long offerId , int offset, int limit);

    List<IcpOrderResponseADMIN> getBySeenBySellerFALSEAndSellerId(String sellerId , int offset, int limit);

    List<IcpOrderResponseADMIN> getByWaitAndAcceptedBySellerWithOffset(int offset, int limit);

    List<IcpOrderResponseADMIN> getBySeenBySellerFALSEWithOffset(int offset, int limit);

    List<IcpOrderResponseADMIN> getByAllActiveWithOffset(int offset, int limit);




    void saveOrder(Orders order);
}


