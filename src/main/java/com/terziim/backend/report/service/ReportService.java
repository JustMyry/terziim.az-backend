package com.terziim.backend.report.service;

import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.report.dto.IcpReportProduct;
import com.terziim.backend.report.dto.IcpReportQuestion;
import com.terziim.backend.report.dto.IcpReportUser;
import com.terziim.backend.report.dto.IcpReportComment;

public interface ReportService {

    IcpResponseModel<String> reportUser(IcpReportUser reportUser);

    IcpResponseModel<String> reportProduct(IcpReportProduct reportProduct);

    IcpResponseModel<String> reportComment(IcpReportComment reportComment);

    IcpResponseModel<String> reportQuestion(IcpReportQuestion reportQuestion);


}
