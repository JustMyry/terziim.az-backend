package com.terziim.backend.activity.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpActivityResponse {

    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private String officer;

    private Integer category;
    private String reason;

    private String effectedType;
    private String effectedId;



    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

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
}
