package com.terziim.backend.tekkart.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.Builder;

import java.util.Date;

@Entity
@Builder
public class TekKart extends BaseModel {


    private String buyerId;
    private String sellerId;

    private Long productId;
    private Long productPrice;
    private Long offeredPrice;
    private String productBrand;
    private String productName;

    private String buyerComment;
    private String payingType;

    private String status;

    private Boolean isActive;



    // Constructors
    public TekKart() {}

    public TekKart(Long id, Date createdAt, Date updatedAt, String buyerId, String sellerId, Long productId,
                   Long productPrice, Long offeredPrice, String productBrand, String productName, String buyerComment,
                   String payingType, String status, Boolean isActive) {
        super(id, createdAt, updatedAt);
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.offeredPrice = offeredPrice;
        this.productBrand = productBrand;
        this.productName = productName;
        this.buyerComment = buyerComment;
        this.payingType = payingType;
        this.status = status;
        this.isActive = isActive;
    }

    public TekKart(String buyerId, String sellerId, Long productId, Long productPrice, Long offeredPrice,
                   String productBrand, String productName, String buyerComment, String payingType, String status,
                   Boolean isActive) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.offeredPrice = offeredPrice;
        this.productBrand = productBrand;
        this.productName = productName;
        this.buyerComment = buyerComment;
        this.payingType = payingType;
        this.status = status;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Long offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBuyerComment() {
        return buyerComment;
    }

    public void setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
    }

    public String getPayingType() {
        return payingType;
    }

    public void setPayingType(String payingType) {
        this.payingType = payingType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
