package com.terziim.backend.category.service;

import com.terziim.backend.category.dto.request.IcpCategoryRequest;
import com.terziim.backend.category.dto.request.IcpSubCategoryRequest;
import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.icpcommication.response.IcpResponseModel;

public interface CategoryExternalServicee {



    public IcpResponseModel<IcpCategoryResponse> addCategory(IcpCategoryRequest payload);

    public IcpResponseModel<IcpSubCategoryResponse> addSubCategory(IcpSubCategoryRequest payload);

    public IcpResponseModel<IcpCategoryResponse> editCategory(IcpCategoryRequest payload, Long id);

    public IcpResponseModel<IcpSubCategoryResponse> editSubCategory(IcpSubCategoryRequest payload, Long id);

    public IcpResponseModel<String> deleteCategory(Long id);

    public IcpResponseModel<String> deleteSubCategory(Long id);


}
