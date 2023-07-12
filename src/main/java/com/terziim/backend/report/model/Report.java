package com.terziim.backend.report.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.Builder;

import java.util.Date;

@Entity
@Builder
public class Report extends BaseModel {

    private String whoHasBeenReported;
    private String whoReported;
    private String type;

    private Boolean isActive;

    private String reportReason;

    private String actionReason;



    //Constractors
    public Report() {}

    public Report(Long id, Date createdAt, Date updatedAt, String whoHasBeenReported, String whoReported, String type, Boolean isActive, String reportReason, String actionReason) {
        super(id, createdAt, updatedAt);
        this.whoHasBeenReported = whoHasBeenReported;
        this.whoReported = whoReported;
        this.type = type;
        this.isActive = isActive;
        this.reportReason = reportReason;
        this.actionReason = actionReason;
    }

    public Report(String whoHasBeenReported, String whoReported, String type, Boolean isActive, String reportReason, String actionReason) {
        this.whoHasBeenReported = whoHasBeenReported;
        this.whoReported = whoReported;
        this.type = type;
        this.isActive = isActive;
        this.reportReason = reportReason;
        this.actionReason = actionReason;
    }



    //Getters and Setters
    public String getWhoHasBeenReported() {
        return whoHasBeenReported;
    }

    public void setWhoHasBeenReported(String whoHasBeenReported) {
        this.whoHasBeenReported = whoHasBeenReported;
    }

    public String getWhoReported() {
        return whoReported;
    }

    public void setWhoReported(String whoReported) {
        this.whoReported = whoReported;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public String getActionReason() {
        return actionReason;
    }

    public void setActionReason(String actionReason) {
        this.actionReason = actionReason;
    }
}
