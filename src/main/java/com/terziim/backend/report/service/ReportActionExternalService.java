package com.terziim.backend.report.service;

import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.report.dto.IcpReportComment;
import com.terziim.backend.report.dto.IcpReportProduct;
import com.terziim.backend.report.dto.IcpReportUser;
import com.terziim.backend.report.model.ReportAction;
import com.terziim.backend.report.model.ReportList;

import java.time.LocalDate;
import java.util.List;

public interface ReportActionExternalService {

    ReportAction getReportActionFromId(Long id);

    List<ReportAction> getReportActionFromReportedIdAndType(String id, String type);

    void reportUserAsAdministrator(IcpReportUser user);

    void reportCommentAsAdministrator(IcpReportComment comment);

    void reportProductAsAdministrator(IcpReportProduct product);

    List<ReportList> getReportListsByDate(LocalDate date);

    List<ReportList> getReportListsByPriority(LocalDate date);

    List<ReportAction> getReportActionsByDate(LocalDate date);



    public IcpResponseModel<List<ReportList>> reportGetListByReporterId(String id, int offset);

    public IcpResponseModel<List<ReportList>> reportGetAllListByActive(int offset);

    public IcpResponseModel<List<ReportList>> reportGetAllActionByActive(int offset);

    public IcpResponseModel<List<ReportList>> reportGetAllList(int offset);

    public IcpResponseModel<List<ReportList>> reportGetAllAction(int offset);

    public IcpResponseModel<List<ReportList>> reportMakeActionSeen(Long id);


}
