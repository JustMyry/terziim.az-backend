package com.terziim.backend.requisition.model;

import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;

import java.util.Date;


@Entity
public class BecomeRequisition extends BaseModel {


    private String senderId;

    private int category;

    private String username;
    private String email;
    private String secondPhone;

    private String address;
    private String openingTime;
    private String closingTime;

    private String description;

    private Boolean isActive;



    // Constractors
    public BecomeRequisition() {}

    public BecomeRequisition(Long id, Date createdAt, Date updatedAt, String senderId, String username, String email,
                             String secondPhone, String address, String openingTime, String closingTime,
                             String description, int category, Boolean isActive) {
        super(id, createdAt, updatedAt);
        this.senderId = senderId;
        this.username = username;
        this.email = email;
        this.secondPhone = secondPhone;
        this.address = address;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.description = description;
        this.category = category;
        this.isActive = isActive;
    }

    public BecomeRequisition(String senderId, String username, String email, String secondPhone, String address,
                             String openingTime, String closingTime, String description, int category, Boolean isActive) {
        this.senderId = senderId;
        this.username = username;
        this.email = email;
        this.secondPhone = secondPhone;
        this.address = address;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.description = description;
        this.category = category;
        this.isActive = isActive;
    }


    // Getters and Setters
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
