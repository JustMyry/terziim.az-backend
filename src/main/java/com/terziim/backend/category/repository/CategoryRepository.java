package com.terziim.backend.category.repository;

import com.terziim.backend.category.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findProductCategoryById(Long id);

    List<ProductCategory>  findAll();

}
