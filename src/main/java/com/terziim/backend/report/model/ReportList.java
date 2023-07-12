package com.terziim.backend.report.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportList extends BaseModel {

    private String whoHasBeenReported;
    private String type;
    private String whoReported;
    private String reportReason;

    private Boolean isActive;

    private String priority;


    // Getters and Setters
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

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //ToString
    @Override
    public String toString() {
        return "ReportList{" +
                "whoHasBeenReported='" + whoHasBeenReported + '\'' +
                ", whoReported='" + whoReported + '\'' +
                ", reportReason='" + reportReason + '\'' +
                ", isActive=" + isActive +
                ", priority='" + priority + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

}
