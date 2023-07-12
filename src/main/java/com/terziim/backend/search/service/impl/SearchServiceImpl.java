package com.terziim.backend.search.service.impl;


import com.terziim.backend.follow.service.FollowExternalService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.mapper.ProductMapper;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.repository.ProductRepository;
import com.terziim.backend.search.service.SearchService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.dto.response.IcpUserResponse;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.impl.UserExternalServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @type: CLASS
 * @author: Indice Inc.
 * @target: Bu sinif istifadeciden gelen melumatlara esasen uygun mehsullari List<> seklinde geri dondurur
 * @params:
 * @comments: Istifadecinin axtaris zamani ede bileceyi olasi yazim yanlislari nezere alinmamisdir
 */

@Service
public class SearchServiceImpl implements SearchService {

    private final ProductRepository repository;
    private final UserExternalServiceImpl userService;
    private final FollowExternalService followService;
    private final JwtProvider jwtProvider;
    public SearchServiceImpl(ProductRepository repository, UserExternalServiceImpl userService,
                             JwtProvider jwtProvider, FollowExternalService followService){
        this.repository = repository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.followService = followService;
    }


    /**
     * @type: METHOD
     * @target:
     * @params:
     * @comments:
     **/
    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameGET(String ad, int offset) {
        List<Product> productsOnFullName = repository.searchOnName(ad, (offset/2), 10);
        List<IcpProductMiniResponse> responses = new ArrayList<>();
        responses.addAll(getProductMiniResponseFromEntity(productsOnFullName));
        String[] partsOfSearchKey = ad.split(" ");
        if(partsOfSearchKey.length>1) {
            List<Product> productsOnFirstWords = repository.searchOnName((partsOfSearchKey[0] + partsOfSearchKey[1]), (offset / 4), 5);
            List<Product> productsOnLastWords = repository.searchOnName((partsOfSearchKey[partsOfSearchKey.length - 1] + partsOfSearchKey[partsOfSearchKey.length - 2]),
                    (offset / 4), 5);
            List<Product> productsOnFirstAndLastWords = repository.searchOnName((partsOfSearchKey[0]),
                    (offset / 4), 5);
            responses.addAll(getProductMiniResponseFromEntity(productsOnFirstWords));
            responses.addAll(getProductMiniResponseFromEntity(productsOnLastWords));
            responses.addAll(getProductMiniResponseFromEntity(productsOnFirstAndLastWords));
        }
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNamePOST(IcpJustJwt payload, String ad, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        List<Product> productsOnFullName = repository.searchOnName(ad, (offset/2), 10);
        List<IcpProductMiniResponse> responses = new ArrayList<>();
        responses.addAll(getProductMiniResponseFromEntityOnUser(productsOnFullName, buyer.getUserId()));
        String[] partsOfSearchKey = ad.split(" ");
        if(partsOfSearchKey.length>1) {
            List<Product> productsOnFirstWords = repository.searchOnName((partsOfSearchKey[0] + partsOfSearchKey[1]), (offset / 4), 5);
            List<Product> productsOnLastWords = repository.searchOnName((partsOfSearchKey[partsOfSearchKey.length - 1] + partsOfSearchKey[partsOfSearchKey.length - 2]),
                    (offset / 4), 5);
            List<Product> productsOnFirstAndLastWords = repository.searchOnName((partsOfSearchKey[0]),
                    (offset / 4), 5);
            responses.addAll(getProductMiniResponseFromEntityOnUser(productsOnFirstWords, buyer.getUserId()));
            responses.addAll(getProductMiniResponseFromEntityOnUser(productsOnLastWords, buyer.getUserId()));
            responses.addAll(getProductMiniResponseFromEntityOnUser(productsOnFirstAndLastWords, buyer.getUserId()));
        }
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnBrandGET(String brend, int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntity(repository.searchOnBrand(brend, offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnBrandPOST(IcpJustJwt payload, String brend, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntityOnUser(repository.searchOnBrand(brend, offset, 20), buyer.getUserId()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }




    @Override
    public IcpResponseModel<IcpUserResponse> searchOnSellerGET(String ad) {
        AppUser seller = userService.findUserByUsername(ad);
        if(seller == null || !seller.isNotLocked())
            return IcpResponseModel.<IcpUserResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.getUserNotFound())
                    .build();
        return IcpResponseModel.<IcpUserResponse>builder()
                .response(userService.getResponseFromUser(seller))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<IcpUserResponse> searchOnSellerPOST(IcpJustJwt payload, String ad) {
        AppUser seller = userService.findUserByUsername(ad);
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller == null || !seller.isNotLocked() || buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<IcpUserResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.getUserNotFound())
                    .build();
        IcpUserResponse response = userService.getResponseFromUser(seller);
        response.setRelation(followService.getRelation(seller.getUserId(), buyer.getUserId()));
        return IcpResponseModel.<IcpUserResponse>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryGET(Long id, int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntity(repository.searchOnCategory(id, offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryPOST(IcpJustJwt payload, Long id, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntityOnUser(repository.searchOnCategory(id, offset, 20), buyer.getUserId()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnSubCategoryGET(Long id, int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntity(repository.searchOnSubCategory(id, offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnSubCategoryPOST(IcpJustJwt payload, Long id, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntityOnUser(repository.searchOnSubCategory(id, offset, 20), buyer.getUserId()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndCategoryGET(String ad, Long id, int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntity(repository.searchOnNameAndCategory(ad, id, offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndCategoryPOST(String ad, IcpJustJwt payload, Long id, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntityOnUser(repository.searchOnNameAndCategory(ad, id, offset, 20), buyer.getUserId()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndGenderGET(String ad, String gender, int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntity(repository.searchOnNameAndGender(ad, gender, offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndGenderPOST(String ad, IcpJustJwt payload, String gender, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntityOnUser(repository.searchOnNameAndGender(ad, gender, offset, 20), buyer.getUserId()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryAndSubCategoryAndGenderGET(Long katId, Long skatId, String gender, int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntity(repository.searchOnCategoryAndSubCategoryAndGender(katId, skatId, gender, offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryAndSubCategoryAndGenderPOST(Long katId, Long skatId, String gender, int offset, IcpJustJwt payload) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntityOnUser(repository.searchOnCategoryAndSubCategoryAndGender(katId, skatId, gender, offset, 20), buyer.getUserId()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchGenderGET(String gender, int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntity(repository.searchOnGender(gender, offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> searchGenderPOST(IcpJustJwt payload, String gender, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(getProductMiniResponseFromEntityOnUser(repository.searchOnGender(gender, offset, 20), buyer.getUserId()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Utility Methods

    private Integer getProductLikes(Long productId) {
        return null;
    }

    private Float getUserLikes(String userId) {
        return null;
    }

    private Boolean doesUserLikedProduct(Long productId, String userId) {
        return false;
    }


    List<IcpProductMiniResponse> getProductMiniResponseFromEntity(List<Product> products) {
        List<IcpProductMiniResponse> responses = products.stream().map(s -> {
            IcpProductMiniResponse product = ProductMapper.INSTANCE.getProductMiniResponse(s);
            AppUser seller = userService.findAppUserByUserId(s.getSellerId());
            product.setSellerType(seller.getUserType());
            product.setSellerName(seller.getUsername());
            product.setSellerLikes(getUserLikes(seller.getUserId()));
            product.setProductLikes(getProductLikes(product.getProductId()));
            return product;
        }).collect(Collectors.toList());
        return responses;
    }


    List<IcpProductMiniResponse> getProductMiniResponseFromEntityOnUser(List<Product> products, String buyerId) {
        List<IcpProductMiniResponse> responses = products.stream().map(s -> {
            IcpProductMiniResponse product = ProductMapper.INSTANCE.getProductMiniResponse(s);
            AppUser seller = userService.findAppUserByUserId(s.getSellerId());
            product.setSellerType(seller.getUserType());
            product.setSellerName(seller.getUsername());
            product.setSellerLikes(getUserLikes(seller.getUserId()));
            product.setProductLikes(getProductLikes(product.getProductId()));
            product.setIsUserLiked(doesUserLikedProduct(product.getProductId(), buyerId));
            return product;
        }).collect(Collectors.toList());
        return responses;
    }

}
