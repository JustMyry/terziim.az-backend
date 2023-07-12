package com.terziim.backend.report.service;

import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.report.dto.IcpReportProduct;
import com.terziim.backend.report.dto.IcpReportUser;
import com.terziim.backend.report.dto.IcpReportComment;

public interface ReportListService {


    IcpResponseModel<String> reportUser(IcpReportUser reportUser);

    IcpResponseModel<String> reportProduct(IcpReportProduct reportProduct);

    IcpResponseModel<String> reportComment(IcpReportComment reportComment);
}
