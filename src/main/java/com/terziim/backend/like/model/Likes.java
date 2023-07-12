package com.terziim.backend.like.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Likes extends BaseModel {

    private String userId;
    private Long productId;



    //Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


    //toString
    @Override
    public String toString() {
        return "Likes{" +
                "userId='" + userId + '\'' +
                ", productId=" + productId +
                '}';
    }
}
