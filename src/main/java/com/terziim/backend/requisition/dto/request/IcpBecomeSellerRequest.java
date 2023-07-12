package com.terziim.backend.requisition.dto.request;


import lombok.Builder;

@Builder
public class IcpBecomeSellerRequest {

    private String jwt;

    private String username;
    private String email;
    private String secondPhone;

    private String address;
    private String openingTime;
    private String closingTime;

    private String description;

    //Constructors
    public IcpBecomeSellerRequest() {}

    public IcpBecomeSellerRequest(String jwt, String username, String email, String secondPhone, String address, String openingTime, String closingTime, String description) {
        this.jwt = jwt;
        this.username = username;
        this.email = email;
        this.secondPhone = secondPhone;
        this.address = address;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.description = description;
    }


    //Getters and Setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
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
}
