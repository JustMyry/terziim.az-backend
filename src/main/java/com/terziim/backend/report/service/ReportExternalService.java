package com.terziim.backend.report.service;

import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.report.dto.IcpReportResponse;

import java.util.List;

public interface ReportExternalService {



    IcpResponseModel<IcpReportResponse> getReportById(Long id);

    IcpResponseModel<List<IcpReportResponse>> getAllReportsByDate(String gun);

    IcpResponseModel<List<IcpReportResponse>> getAllReportsByDateAndType(String gun, String type);

    IcpResponseModel<List<IcpReportResponse>> getReportFromWhoReportedAndType(String category, String userId);

    IcpResponseModel<List<IcpReportResponse>> getReportFromWhoHasBeenReportedAndType(String category, String userId);

    IcpResponseModel<String> makeReportSeen(Long reportId);
}
