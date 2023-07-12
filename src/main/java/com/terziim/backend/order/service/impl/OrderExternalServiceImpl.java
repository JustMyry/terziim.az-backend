package com.terziim.backend.order.service.impl;


import com.terziim.backend.order.dto.request.IcpOrderRequest;
import com.terziim.backend.order.dto.response.IcpOrderResponseADMIN;
import com.terziim.backend.order.mapper.OrderMapper;
import com.terziim.backend.order.model.Orders;
import com.terziim.backend.order.repository.OrderRepository;
import com.terziim.backend.order.service.OrderExternalService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderExternalServiceImpl implements OrderExternalService {

    private final OrderRepository repository;
    public OrderExternalServiceImpl(OrderRepository repository){
        this.repository = repository;
    }



    @Override
    public void updateOrder(IcpOrderRequest payload, Long id) {
        Orders order = repository.getById(id);
        OrderMapper.INSTANCE.updateOrder(order, payload);
        repository.save(order);
    }

    @Override
    public void changeStatusToEXAM(Long orderId) {
        Orders order = repository.getById(orderId);
        order.setStatus("EXAM");
        repository.save(order);
    }

    @Override
    public void changeStatusToCARGO(Long orderId) {
        Orders order = repository.getById(orderId);
        order.setStatus("CARGO");
        repository.save(order);
    }

    @Override
    public void changeStatusToFINISHED(Long orderId) {
        Orders order = repository.getById(orderId);
        order.setStatus("FINISHED");
        order.setActive(false);
        repository.save(order);
    }

    @Override
    public void changeActivityToParam(Boolean avtivity, Long orderId) {
        Orders order = repository.getById(orderId);
        order.setActive(avtivity);
        repository.save(order);
    }

    @Override
    public Orders getOrderById(Long id) {
        return repository.getById(id);
    }


    @Override
    public IcpOrderResponseADMIN getOrderResponse(Long id) {
        return OrderMapper.INSTANCE.getOrderResponseAdmin(repository.getById(id));
    }

    @Override
    public List<Orders> getOrdersByBuyerIdAndProductIdWithOffset(String buyerId, Long productId, int offset, int limit) {
        return repository.getOrdersByBuyerIdAndProductIdWithOffset(buyerId, productId, offset, limit);
    }


    @Override
    public List<IcpOrderResponseADMIN> getOrdersBySellerIdWithOffset(String sellerId, int offset, int limit) {
        return getOrderResponseList(repository.getBySellerIdWithOffset(sellerId, offset, limit));
    }


    @Override
    public List<IcpOrderResponseADMIN> getOrdersByBuyerIdWithOffset(String buyerId, int offset, int limit) {
        return getOrderResponseList(repository.getByBuyerIdWithOffset(buyerId, offset, limit));
    }

    @Override
    public List<IcpOrderResponseADMIN> getByStatusEXSAMWithOffset(int offset, int limit) {
        return getOrderResponseList(repository.getByStatusEXSAMWithOffset(offset, limit));
    }

    @Override
    public List<IcpOrderResponseADMIN> getByStatusWAITWithOffset(int offset, int limit) {
        return getOrderResponseList(repository.getByStatusWAITWithOffset(offset, limit));
    }

    @Override
    public List<IcpOrderResponseADMIN> getByStatusCARGOWithOffset(int offset, int limit) {
        return getOrderResponseList(repository.getByStatusCARGOWithOffset(offset, limit));
    }

    @Override
    public List<IcpOrderResponseADMIN> getByStatusCFSandSellerId(String sellerId) {
        return getOrderResponseList(repository.getByStatusCFSandSellerId(sellerId));
    }

    @Override
    public List<IcpOrderResponseADMIN> getByStatusCFBandBuyerId(String buyerId) {
        return getOrderResponseList(repository.getByStatusCFBandBuyerId(buyerId));
    }

    @Override
    public List<IcpOrderResponseADMIN> getByOfferId(Long offerId, int offset, int limit) {
        return getOrderResponseList(repository.getByOfferId(offerId, offset, limit));
    }

    @Override
    public List<IcpOrderResponseADMIN> getBySeenBySellerFALSEAndSellerId(String sellerId, int offset, int limit) {
        return getOrderResponseList(repository.getBySeenBySellerFALSEAndSellerIdWithOffset(sellerId, offset, limit));
    }


    @Override
    public List<IcpOrderResponseADMIN> getByWaitAndAcceptedBySellerWithOffset(int offset, int limit) {
        return getOrderResponseList(repository.getByWaitAndAcceptedBySellerWithOffset(offset, limit));
    }

    @Override
    public List<IcpOrderResponseADMIN> getBySeenBySellerFALSEWithOffset(int offset, int limit) {
        return getOrderResponseList(repository.getBySeenBySellerFALSEWithOffset(offset, limit));
    }

    @Override
    public List<IcpOrderResponseADMIN> getByAllActiveWithOffset(int offset, int limit) {
        return getOrderResponseList(repository.getByAllActiveWithOffset(offset, limit));
    }


    @Override
    public void saveOrder(Orders order) {
        repository.save(order);
    }


    // Utility Methods
    List<IcpOrderResponseADMIN> getOrderResponseList(List<Orders> orders){
        return orders.stream().map(s->{
            return OrderMapper.INSTANCE.getOrderResponseAdmin(s);
        }).collect(Collectors.toList());
    }
}
