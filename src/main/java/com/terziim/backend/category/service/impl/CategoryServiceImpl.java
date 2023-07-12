package com.terziim.backend.category.service.impl;

import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.category.mapper.CategoryMapper;
import com.terziim.backend.category.mapper.SubCategoryMapper;
import com.terziim.backend.category.repository.CategoryRepository;
import com.terziim.backend.category.repository.SubCategoryRepository;
import com.terziim.backend.category.service.CategoryService;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository){
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }




    @Override
    public IcpResponseModel<List<IcpCategoryResponse>> showCategories() {
        List<IcpCategoryResponse> responses = categoryRepository.findAll().stream().map(s-> {
            IcpCategoryResponse r = CategoryMapper.INSTANCE.getCategoryResponse(s);
            r.setId(s.getId());
            return r;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpCategoryResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<List<IcpSubCategoryResponse>> showSubCategories() {
        List<IcpSubCategoryResponse> responses = subCategoryRepository.findAll().stream().map(s->{
            IcpSubCategoryResponse r = SubCategoryMapper.INSTANCE.getSubCategoryResponse(s);
            r.setId(s.getId());
            return r;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpSubCategoryResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }




}
