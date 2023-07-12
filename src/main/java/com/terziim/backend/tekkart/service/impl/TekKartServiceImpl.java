package com.terziim.backend.tekkart.service.impl;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.order.service.OrderExternalService;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.tekkart.dto.request.IcpTekKartRequest;
import com.terziim.backend.tekkart.mapper.TekKartMapper;
import com.terziim.backend.tekkart.model.TekKart;
import com.terziim.backend.tekkart.repository.TekKartRepository;
import com.terziim.backend.tekkart.service.TekKartService;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TekKartServiceImpl implements TekKartService {

    private final TekKartRepository repository;
    private final ProductExternalService productService;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    private final OrderExternalService orderService;
    public TekKartServiceImpl(TekKartRepository repository, ProductExternalService productService, UserExternalService userService,
                              JwtProvider jwtProvider, OrderExternalService orderService) {
        this.repository = repository;
        this.productService = productService;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.orderService = orderService;
    }



    @Override
    public IcpResponseModel<String> sendTekKart(IcpTekKartRequest request) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(request.getJwt()));
        Product product = productService.getProductById(request.getProductId());
        if(user==null || !user.isNotLocked() || product==null || !productService.doesSellerAcceptOfferForProduct(request.getProductId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        TekKart offer = TekKartMapper.INSTANCE.getTekKartFromRequest(request);
        offer.setBuyerId(user.getUserId());
        repository.save(offer);
        return IcpResponseModel.<String>builder()
                .response("Təklifiniz uğurla göndərildi!")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpTekKartRequest>> showAllToSellers(IcpJustJwt request, int offset) {
        return null;
    }

    @Override
    public IcpResponseModel<IcpTekKartRequest> showSpecificToSeller(IcpJustJwt request, Long id) {
        return null;
    }

    @Override
    public IcpResponseModel<IcpTekKartRequest> showSpecificToBuyer(IcpJustJwt request, Long id) {
        return null;
    }

    @Override
    public IcpResponseModel<List<IcpTekKartRequest>> showAllToBuyer(IcpJustJwt request, int offset) {
        return null;
    }

    @Override
    public IcpResponseModel<String> acceptTekKart(IcpJustJwt request) {
        return null;
    }

    @Override
    public IcpResponseModel<String> declineTekKart(IcpJustJwt request) {
        return null;
    }

    @Override
    public IcpResponseModel<String> rejectTekKartProcessAsSeller(IcpJustJwt request) {
        return null;
    }

    @Override
    public IcpResponseModel<String> rejectTekKartProcessAsBuyer(IcpJustJwt request) {
        return null;
    }
}
