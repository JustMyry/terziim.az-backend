package com.terziim.backend.order.service.impl;


import com.terziim.backend.activity.service.ActivityExternalService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.order.dto.request.IcpOrderRequest;
import com.terziim.backend.order.dto.response.IcpOrderResponseBUYER;
import com.terziim.backend.order.dto.response.IcpOrderResponseSELLER;
import com.terziim.backend.order.mapper.OrderMapper;
import com.terziim.backend.order.model.Orders;
import com.terziim.backend.order.repository.OrderRepository;
import com.terziim.backend.order.service.OrderService;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.model.SizeCount;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.report.service.ReportActionExternalService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.terziim.backend.constants.OrderConstants.BUYER_CANCEL_LIMIT;
import static com.terziim.backend.constants.OrderConstants.SELLER_CANCEL_LIMIT;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    private final ReportActionExternalService reportService;
    private final ProductExternalService productService;
    private final ActivityExternalService activity;
    public OrderServiceImpl(OrderRepository repository, UserExternalService userService, JwtProvider jwtProvider,
                            ReportActionExternalService reportService, ProductExternalService productService,
                            ActivityExternalService activity){
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.repository = repository;
        this.reportService = reportService;
        this.productService = productService;
        this.activity = activity;
    }


    // Buyer Resource methods
    @Override
    public IcpResponseModel<IcpOrderResponseBUYER> giveAnOrder(IcpOrderRequest payload) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Product product = productService.getProductById(payload.getProductId());
        if(buyer==null || product==null || !buyer.isNotLocked() || !verifyOrderRequest(payload, product))
            return IcpResponseModel.<IcpOrderResponseBUYER>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Orders order = OrderMapper.INSTANCE.getOrderFromRequest(payload);
        order.setPrice(product.getProductPrice());
        order.setSellerId(product.getSellerId());
        order.setBuyerId(buyer.getUserId());
        repository.save(order);
        return IcpResponseModel.<IcpOrderResponseBUYER>builder()
                .response(OrderMapper.INSTANCE.getOrderResponseBuyer(order))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpOrderResponseBUYER>> getActiveOrdersOfBuyer(IcpJustJwt payload, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpOrderResponseBUYER>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<IcpOrderResponseBUYER> response = repository.getByAcitveAndBuyerIdWithOffset(buyer.getUserId(), offset, 20).stream().map(
                s-> {
                    return getResponseForBuyer(s);
                }
        ).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpOrderResponseBUYER>>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpOrderResponseBUYER>> getAllOrdersOfBuyer(IcpJustJwt payload, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpOrderResponseBUYER>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<IcpOrderResponseBUYER> response = repository.getByBuyerIdWithOffset(buyer.getUserId(), offset, 20).stream().map(
                s-> {
                    return getResponseForBuyer(s);
                }
        ).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpOrderResponseBUYER>>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpOrderResponseBUYER> getSpecialOrderForBuyer(IcpJustJwt payload, Long orderId) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Orders order = repository.getByIdActive(orderId);
        if(buyer==null || !buyer.isNotLocked() || order==null)
            return IcpResponseModel.<IcpOrderResponseBUYER>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<IcpOrderResponseBUYER>builder()
                .response(getResponseForBuyer(order))
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }

    @Override
    public IcpResponseModel<String> rejectOrderFromBuyer(IcpJustJwt payload, Long orderId) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Orders order = repository.getByIdActive(orderId);
        if(buyer==null || !buyer.isNotLocked() || order==null || !order.getBuyerId().equals(buyer.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        if(order.getSeenBySeller()==false){
            repository.delete(order);
            return IcpResponseModel.<String>builder()
                    .response("Sifaris ugurla iptal edildi! Satici terefinden tesdiqlenmediyi ucun menfi bal yazilamdi.")
                    .status(IcpResponseStatus.getSuccess())
                    .build();
        }
        else {
            order.setStatus("CFB");
            order.setActive(false);
            repository.save(order);
            if(repository.getByStatusCFBandBuyerId(buyer.getUserId()).size() > BUYER_CANCEL_LIMIT){
                proccedureCFB(buyer);
                return IcpResponseModel.<String>builder()
                        .response("Sifaris ugurla iptal edildi! Iptal limitini kecdiyiniz ucun hesab incelemeye alindi.")
                        .status(IcpResponseStatus.getSuccess())
                        .build();
            }
            return IcpResponseModel.<String>builder()
                    .response("Sifaris ugurla iptal edildi! Satici terefinden tesdiqlendiyi ucun menfi bal yazildi. (Limit: " + BUYER_CANCEL_LIMIT + ")")
                    .status(IcpResponseStatus.getSuccess())
                    .build();
        }
    }




    // Seller Resource Methdods

    @Override
    public IcpResponseModel<List<IcpOrderResponseSELLER>> getActiveOrdersOfSeller(IcpJustJwt payload, int offset) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller==null || !seller.isNotLocked())
            return IcpResponseModel.<List<IcpOrderResponseSELLER>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<List<IcpOrderResponseSELLER>>builder()
                .response(getIcpOrderResponseSELLERList(repository.getByAcitveAnSellerIdWithOffset(seller.getUserId(), offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpOrderResponseSELLER>> getUnrepliedOrdersOfSeller(IcpJustJwt payload, int offset) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller==null || !seller.isNotLocked())
            return IcpResponseModel.<List<IcpOrderResponseSELLER>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<List<IcpOrderResponseSELLER>>builder()
                .response(getIcpOrderResponseSELLERList(repository.getBySeenBySellerFALSEAndSellerIdWithOffset(seller.getUserId(), offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpOrderResponseSELLER>> getFinishedOrdersOfSeller(IcpJustJwt payload, int offset) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller==null || !seller.isNotLocked())
            return IcpResponseModel.<List<IcpOrderResponseSELLER>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<List<IcpOrderResponseSELLER>>builder()
                .response(getIcpOrderResponseSELLERList(repository.getByAcitveAnSellerIdWithOffset(seller.getUserId(), offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> rejectOrderBySeller(IcpJustJwt payload, Long orderId) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Orders order = repository.getByIdActive(orderId);
        if(seller==null || !seller.isNotLocked() || order==null || !order.getSellerId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        order.setStatus("CFS");
        order.setActive(false);
        repository.save(order);
        if(repository.getByStatusCFSandSellerId(seller.getUserId()).size() > SELLER_CANCEL_LIMIT){
            proccedureCFS(seller);
            return IcpResponseModel.<String>builder()
                    .response("Sifaris ugurla iptal edildi! Iptal limitini kecdiyiniz ucun hesab incelemeye alindi.")
                    .status(IcpResponseStatus.getSuccess())
                    .build();
        }
        return IcpResponseModel.<String>builder()
                .response("Sifaris ugurla iptal edildi! Satici terefinden tesdiqlendiyi ucun menfi bal yazildi. (Limit: " + SELLER_CANCEL_LIMIT + ")")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<IcpOrderResponseSELLER> getSpecialOrderForSeller(IcpJustJwt payload, Long orderId) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Orders order = repository.getByIdActive(orderId);
        if(seller==null || !seller.isNotLocked() || order==null)
            return IcpResponseModel.<IcpOrderResponseSELLER>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<IcpOrderResponseSELLER>builder()
                .response(getResponseForSeller(order))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> acceptOrderBySeller(IcpJustJwt payload, Long orderId) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Orders order = repository.getByIdActive(orderId);
        if(seller==null || !seller.isNotLocked() || order==null || !order.getSellerId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        order.setSeenBySeller(true);
        order.setAcceptedBySeller(true);
        repository.save(order);
        return IcpResponseModel.<String>builder()
                .response("Sifaris Muveffeqiyyetle qebul edildi!")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> declineOrderBySeller(IcpJustJwt payload, Long orderId) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Orders order = repository.getByIdActive(orderId);
        if(seller==null || !seller.isNotLocked() || order==null || !order.getSellerId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        order.setSeenBySeller(true);
        order.setAcceptedBySeller(false);
        order.setActive(false);
        repository.save(order);
        return IcpResponseModel.<String>builder()
                .response("Sifaris Muveffeqiyyetle inkar edildi!")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    //Utility Methods
    private List<IcpOrderResponseSELLER> getIcpOrderResponseSELLERList(List<Orders> orders){
        return orders.stream().map(s->{
            return getResponseForSeller(s);
        }).collect(Collectors.toList());
    }


    private boolean verifyOrderRequest(IcpOrderRequest payload, Product product) {
        Integer size = product.getSizeCounts().stream().filter(s-> s.getSize().equals(payload.getSize())).findFirst().get().getCount();
        if(size == null || size<payload.getCount())
            return false;
        else
            return true;
    }


    private void proccedureCFB(AppUser buyer) {
        userService.makeUserLocked(buyer);
        activity.createCFBactivity(buyer.getUserId());
    }


    private void proccedureCFS(AppUser seller) {
        userService.makeUserLocked(seller);
        activity.createCFSactivity(seller.getUserId());
    }

    IcpOrderResponseBUYER getResponseForBuyer(Orders o){
        Product p = productService.getProductById(o.getProductId());
        IcpOrderResponseBUYER r = OrderMapper.INSTANCE.getOrderResponseBuyer(o);
        r.setProductBrand(p.getProductBrand());
        r.setProductName(p.getProductName());
        r.setProductPictureUrl(p.getProductPictureUrl());
        return r;
    }


    IcpOrderResponseSELLER getResponseForSeller(Orders o){
        Product p = productService.getProductById(o.getProductId());
        IcpOrderResponseSELLER r = OrderMapper.INSTANCE.getOrderResponseSeller(o);
        r.setProductBrand(p.getProductBrand());
        r.setProductName(p.getProductName());
        r.setProductPictureUrl(p.getProductPictureUrl());
        return r;
    }
}
