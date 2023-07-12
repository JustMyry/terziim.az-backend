package com.terziim.backend.search.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.user.dto.response.IcpUserResponse;

import java.util.List;

public interface SearchService {


    IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameGET(String ad, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnNamePOST(IcpJustJwt payload, String ad, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnBrandGET(String brend, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnBrandPOST(IcpJustJwt payload, String brend, int offset);

    IcpResponseModel<IcpUserResponse> searchOnSellerGET(String ad);

    IcpResponseModel<IcpUserResponse> searchOnSellerPOST(IcpJustJwt payload, String ad);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryGET(Long id, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryPOST(IcpJustJwt payload, Long id, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnSubCategoryGET(Long id, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnSubCategoryPOST(IcpJustJwt payload, Long id, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndCategoryGET(String ad, Long id, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndCategoryPOST(String ad, IcpJustJwt payload, Long id, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndGenderGET(String ad, String gender, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnNameAndGenderPOST(String ad, IcpJustJwt payload, String gender, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryAndSubCategoryAndGenderGET(Long katId, Long skatId, String gender, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchOnCategoryAndSubCategoryAndGenderPOST(Long katId, Long skatId, String gender, int offset, IcpJustJwt payload);

    IcpResponseModel<List<IcpProductMiniResponse>> searchGenderGET(String gender, int offset);

    IcpResponseModel<List<IcpProductMiniResponse>> searchGenderPOST(IcpJustJwt payload, String gender, int offset);
}
