package com.terziim.backend.star.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Star extends BaseModel {


    private String sellerId;
    private String buyerId;

    private Float star;

    private Boolean isActive;



    //Getters and Setters
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }



    //toString
    @Override
    public String toString() {
        return "Star{" +
                "sellerId='" + sellerId + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", star=" + star +
                ", isActive=" + isActive +
                '}';
    }
}
