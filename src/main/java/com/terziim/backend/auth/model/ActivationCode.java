package com.terziim.backend.auth.model;

import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Date;


@Entity
public class ActivationCode extends BaseModel {

    @Column(nullable = false, updatable = false)
    private String code;
    private String userName;
    private Date expirationDate;


    public ActivationCode() { }

    public ActivationCode(String code, String userName, Date expirationDate) {
        this.code = code;
        this.userName = userName;
        this.expirationDate = expirationDate;
    }

    //Getters and Setters
    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public Date getExpirationDate() { return expirationDate; }

    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }

    //RandomStringUtils.randomNumeric(10);

}
