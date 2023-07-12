package com.terziim.backend.category.resource;


import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.category.service.CategoryService;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryResource {


    private final CategoryService service;
    public CategoryResource(CategoryService service){
        this.service = service;
    }


    @GetMapping("/show/subcategories")
    public IcpResponseModel<List<IcpSubCategoryResponse>> showSubCategories(){
        return service.showSubCategories();
    }


    @GetMapping("/show/categories")
    public IcpResponseModel<List<IcpCategoryResponse>> showCategories(){
        return service.showCategories();
    }




}
