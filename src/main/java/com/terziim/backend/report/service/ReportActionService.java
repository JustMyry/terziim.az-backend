package com.terziim.backend.report.service;

import com.terziim.backend.report.model.ReportList;

import java.util.List;

public interface ReportActionService {
    void userReportedOnLimit(String id, String priority);

    void userReportedEnough(String id, ReportList report, List<ReportList> list);

    void productReportedEnough(Long id, ReportList report, List<ReportList> list);

    void commentReportedEnough(Long id, ReportList report, List<ReportList> list);
}
