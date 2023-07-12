package com.terziim.backend.product.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.request.IcpSingleData;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.dto.request.IcpClothesRequest;
import com.terziim.backend.product.dto.request.IcpProductRequest;
import com.terziim.backend.product.dto.request.IcpShoesRequest;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;

import java.util.List;

public interface ProductService {




    IcpResponseModel<IcpProductDetailedResponse> showProductForSpecificUser(Long productId, IcpJustJwt payload);

    IcpResponseModel<IcpProductDetailedResponse> showProductForGuest(Long productId);


    IcpResponseModel<List<IcpProductMiniResponse>> showNewProductsForGuest(int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> showNewVIPProductsForGuest(int offset);

    //IcpResponseModel<List<IcpProductMiniResponse>> showSpecialProductsForUser(IcpJustJwt payload, int offset);

    IcpResponseModel<String> deleteProduct(IcpJustJwt payload, Long id);

    IcpResponseModel<String> changeStatusOfProductToSold(IcpJustJwt payload, Long id);

    IcpResponseModel<String> changeStatusOfProductToExists(IcpJustJwt payload, Long id);

    IcpResponseModel<List<IcpProductMiniResponse>> showProductsOfUserToUser(String accId, IcpJustJwt payload, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> showProductsOfUserToGuest(String accId, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> showNewProductsForUser(int offset, IcpJustJwt payload);

    IcpResponseModel<List<IcpProductMiniResponse>> showNewVIPProductsForUser(int offset, IcpJustJwt payload);

    IcpResponseModel<IcpProductDetailedResponse> makeDiscount(Long productId, IcpSingleData payload);

    IcpResponseModel<List<IcpProductMiniResponse>> showSimilarProducts(Long subCatId, int offset);

    IcpResponseModel<IcpProductDetailedResponse> addClothesProduct(IcpClothesRequest payload);

    IcpResponseModel<IcpProductDetailedResponse> addShoesProduct(IcpShoesRequest payload);

    IcpResponseModel<IcpProductDetailedResponse> addOtherProduct(IcpProductRequest payload);

    IcpResponseModel<String> changeCountOfSizeCount(IcpJustJwt payload, Integer say, String size, Long productId);
}
