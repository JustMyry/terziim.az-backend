package com.terziim.backend.offer.service.impl;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.like.service.LikeExternalService;
import com.terziim.backend.offer.service.OfferService;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class OfferServiceImpl implements OfferService {

    private final ProductExternalService pService;
    private final UserExternalService uService;
    private final JwtProvider jwtProvider;
    private final LikeExternalService lService;
    public OfferServiceImpl(ProductExternalService pService, UserExternalService uService, JwtProvider jwtProvider, LikeExternalService lService) {
        this.pService = pService;
        this.uService = uService;
        this.jwtProvider = jwtProvider;
        this.lService = lService;
    }



    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showOffersToUser(IcpJustJwt payload) {
        AppUser user = uService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(user==null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<Product> likedP = lService.getUsersLikes(user.getUserId()).stream().map(s-> pService.getProductById(s.getProductId())).toList();
        List<Long> subCategories = convertSetToList(likedP.stream().map(s->s.getSubCategoryId()).collect(Collectors.toSet()));
        List<Product> products = pService.getProductFromSubCatWithLimit(subCategories.get(0), 10);
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(pService.getProductMiniRespsWithSubCatFromProducts(products))
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }



    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showAllOffersToUser(IcpJustJwt payload) {
        AppUser user = uService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(user==null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<Product> likedP = lService.getUsersLikes(user.getUserId()).stream().map(s-> pService.getProductById(s.getProductId())).toList();
        List<Long> subCategories = convertSetToList(likedP.stream().map(s->s.getSubCategoryId()).collect(Collectors.toSet()));
        List<Product> products = pService.getProductFromSubCatWithLimit(subCategories.get(0), 40);
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(pService.getProductMiniRespsWithSubCatFromProducts(products))
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }


    // Utility methods
    public static <T> List<T> convertSetToList(Set<T> set)
    {
        // create an empty list
        List<T> list = new ArrayList<>();

        // push each element in the set into the list
        for (T t : set)
            list.add(t);

        // return the list
        return list;
    }


}
