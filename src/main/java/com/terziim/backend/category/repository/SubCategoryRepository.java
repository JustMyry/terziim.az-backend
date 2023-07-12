package com.terziim.backend.category.repository;

import com.terziim.backend.category.model.ProductSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubCategoryRepository extends JpaRepository<ProductSubCategory, Long> {

    ProductSubCategory findProductSubCategoryById(Long id);

    List<ProductSubCategory> findAll();
}
