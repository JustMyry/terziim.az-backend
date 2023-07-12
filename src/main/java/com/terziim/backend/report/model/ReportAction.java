package com.terziim.backend.report.model;

import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportAction extends BaseModel {

    private String whoHasBeenReported;
    private String type;

    private Boolean isActive;

    private String reportReason;

    private String actionReason;

    @OneToMany
    @JoinColumn(name = "id")
    private List<ReportList> reportList;



    //Getters and Setters
    public String getWhoHasBeenReported() {
        return whoHasBeenReported;
    }

    public void setWhoHasBeenReported(String whoHasBeenReported) {
        this.whoHasBeenReported = whoHasBeenReported;
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

    public List<ReportList> getReportList() {
        return reportList;
    }

    public void setReportList(List<ReportList> reportList) {
        this.reportList = reportList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // toString
    @Override
    public String toString() {
        return "ReportAction{" +
                "whoHasBeenReported='" + whoHasBeenReported + '\'' +
                ", isActive=" + isActive +
                ", reportReason='" + reportReason + '\'' +
                ", actionReason='" + actionReason + '\'' +
                ", reportList=" + reportList +
                ", type=" + type +
                '}';
    }


}
