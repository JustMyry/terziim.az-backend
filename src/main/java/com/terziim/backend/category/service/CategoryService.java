package com.terziim.backend.category.service;

import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.icpcommication.response.IcpResponseModel;

import java.util.List;

public interface CategoryService {

    IcpResponseModel<List<IcpCategoryResponse>> showCategories();

    IcpResponseModel<List<IcpSubCategoryResponse>> showSubCategories();

}
