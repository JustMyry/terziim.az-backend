package com.terziim.backend.activity.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.NonNull;

import java.util.Date;

@Entity
@Builder
public class Activity extends BaseModel {

    @NonNull
    private String officer;

    @NonNull
    private Integer category;
    private String reason;

    @NonNull
    private String effectedType;

    @NonNull
    private String effectedId;

    private Boolean isActive;


    //Constructors
    public Activity() {}

    public Activity(Long id, Date createdAt, Date updatedAt, @NonNull String officer, @NonNull Integer category,
                    String reason, @NonNull String effectedType, @NonNull String effectedId, Boolean isActive) {
        super(id, createdAt, updatedAt);
        this.officer = officer;
        this.category = category;
        this.reason = reason;
        this.effectedType = effectedType;
        this.effectedId = effectedId;
        this.isActive = isActive;
    }

    public Activity(@NonNull String officer, @NonNull Integer category, String reason, @NonNull String effectedType,
                    @NonNull String effectedId, Boolean isActive) {
        this.officer = officer;
        this.category = category;
        this.reason = reason;
        this.effectedType = effectedType;
        this.effectedId = effectedId;
        this.isActive = isActive;
    }

    //Getters and Setters
    public String getOfficer() {
        return officer;
    }

    public void setOfficer(String officer) {
        this.officer = officer;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getEffectedType() {
        return effectedType;
    }

    public void setEffectedType(String effectedType) {
        this.effectedType = effectedType;
    }

    public String getEffectedId() {
        return effectedId;
    }

    public void setEffectedId(String effectedId) {
        this.effectedId = effectedId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
