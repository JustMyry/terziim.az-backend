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
public class ProductCategory extends BaseModel {

    private String categoryName;
    private String about;


    //Getters and Setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


    //toString
    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryName='" + categoryName + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}