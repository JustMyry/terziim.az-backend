package com.terziim.backend.category.service.impl;

import com.terziim.backend.category.dto.request.IcpCategoryRequest;
import com.terziim.backend.category.dto.request.IcpSubCategoryRequest;
import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.category.mapper.CategoryMapper;
import com.terziim.backend.category.mapper.SubCategoryMapper;
import com.terziim.backend.category.model.ProductCategory;
import com.terziim.backend.category.model.ProductSubCategory;
import com.terziim.backend.category.repository.CategoryRepository;
import com.terziim.backend.category.repository.SubCategoryRepository;
import com.terziim.backend.category.service.CategoryExternalServicee;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import org.springframework.stereotype.Service;


@Service
public class CategoryExternalServiceImpl implements CategoryExternalServicee {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    public CategoryExternalServiceImpl(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository){
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }


    @Override
    public IcpResponseModel<IcpCategoryResponse> addCategory(IcpCategoryRequest payload) {
        ProductCategory category = CategoryMapper.INSTANCE.getCategoryFromRequest(payload);
        categoryRepository.save(category);
        IcpCategoryResponse response = CategoryMapper.INSTANCE.getCategoryResponse(category);
        response.setId(category.getId());
        return IcpResponseModel.<IcpCategoryResponse>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }




    @Override
    public IcpResponseModel<IcpSubCategoryResponse> addSubCategory(IcpSubCategoryRequest payload) {
        ProductSubCategory subCategory = SubCategoryMapper.INSTANCE.getSubCategoryFromRequest(payload);
        subCategoryRepository.save(subCategory);
        IcpSubCategoryResponse response = SubCategoryMapper.INSTANCE.getSubCategoryResponse(subCategory);
        response.setId(subCategory.getId());
        return IcpResponseModel.<IcpSubCategoryResponse>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<IcpCategoryResponse> editCategory(IcpCategoryRequest payload, Long id) {
        ProductCategory category = categoryRepository.findProductCategoryById(id);
        if(category==null)
            return IcpResponseModel.<IcpCategoryResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        category.setCategoryName(payload.getCategoryName());
        category.setAbout(payload.getAbout());
        IcpCategoryResponse response = CategoryMapper.INSTANCE.getCategoryResponse(category);
        response.setId(category.getId());
        categoryRepository.save(category);
        return IcpResponseModel.<IcpCategoryResponse>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<IcpSubCategoryResponse> editSubCategory(IcpSubCategoryRequest payload, Long id) {
        ProductSubCategory subCategory = subCategoryRepository.findProductSubCategoryById(id);
        if(subCategory==null)
            return IcpResponseModel.<IcpSubCategoryResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        subCategory.setCategoryId(payload.getCategoryId());
        subCategory.setSubCategoryName(payload.getSubCategoryName());
        subCategory.setAbout(payload.getAbout());
        IcpSubCategoryResponse response = SubCategoryMapper.INSTANCE.getSubCategoryResponse(subCategory);
        response.setId(subCategory.getId());
        subCategoryRepository.save(subCategory);
        return IcpResponseModel.<IcpSubCategoryResponse>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<String> deleteCategory(Long id) {
        ProductCategory category = categoryRepository.findProductCategoryById(id);
        if(category==null)
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        categoryRepository.delete(category);
        return IcpResponseModel.<String>builder()
                .response("Category Deleted")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> deleteSubCategory(Long id) {
        ProductSubCategory subCategory = subCategoryRepository.findProductSubCategoryById(id);
        if(subCategory==null)
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        subCategoryRepository.delete(subCategory);
        return IcpResponseModel.<String>builder()
                .response("SubCategory Deleted")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


}
