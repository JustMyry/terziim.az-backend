package com.terziim.backend.comment.model;

import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseModel {


    private String senderId;
    private Long productId;

    @Column(length = 500)
    private String comment;
    private Boolean userBoughtProduct;

    private Boolean isActive;


    // Getters and Setters
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getUserBoughtProduct() {
        return userBoughtProduct;
    }

    public void setUserBoughtProduct(Boolean userBoughtProduct) {
        this.userBoughtProduct = userBoughtProduct;
    }
}
