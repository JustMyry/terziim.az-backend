package com.terziim.backend.requisition.model;


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
public class Requisition extends BaseModel {


    @Column(nullable = false)
    private String senderId;

    @Column(nullable = false)
    private int requisitionCategoryId;



    @Column(nullable = false,  length = 100)
    private String requisitionHeader;

    @Column(length = 700)
    private String requisitionText;

    private Boolean isActive;


    //Getters and Setters
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public int getRequisitionCategoryId() {
        return requisitionCategoryId;
    }

    public void setRequisitionCategoryId(int requisitionCategoryId) {
        this.requisitionCategoryId = requisitionCategoryId;
    }

    public String getRequisitionHeader() {
        return requisitionHeader;
    }

    public void setRequisitionHeader(String requisitionHeader) {
        this.requisitionHeader = requisitionHeader;
    }

    public String getRequisitionText() {
        return requisitionText;
    }

    public void setRequisitionText(String requisitionText) {
        this.requisitionText = requisitionText;
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
        return "Requisition{" +
                "senderId='" + senderId + '\'' +
                ", requisitionCategoryId=" + requisitionCategoryId +
                ", requisitionHeader='" + requisitionHeader + '\'' +
                ", requisitionText='" + requisitionText + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
