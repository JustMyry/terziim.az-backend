package com.terziim.backend.category.model;

import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSubCategory extends BaseModel {

    private Long categoryId;
    private String subCategoryName;
    private String about;

//
//    @ManyToOne
//    @JoinColumn(name = "id")
//    private ProductCategory upperCategory;


    ///Getters and Setters
    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }



    //toString
    @Override
    public String toString() {
        return "ProductSubCategory{" +
                "categoryId=" + categoryId +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
