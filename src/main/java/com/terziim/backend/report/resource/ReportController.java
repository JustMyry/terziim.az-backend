package com.terziim.backend.report.resource;

import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.report.dto.IcpReportComment;
import com.terziim.backend.report.dto.IcpReportProduct;
import com.terziim.backend.report.dto.IcpReportQuestion;
import com.terziim.backend.report.dto.IcpReportUser;
import com.terziim.backend.report.service.impl.ReportServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportServiceImpl service;
    public ReportController(ReportServiceImpl service){
        this.service = service;
    }

    @PostMapping("/user")
    public IcpResponseModel<String> reportUser(@RequestBody IcpReportUser reportUser){
        return service.reportUser(reportUser);
    }

    @PostMapping("/product")
    public IcpResponseModel<String> reportProduct(@RequestBody IcpReportProduct reportProduct){
        return service.reportProduct(reportProduct);
    }

    @PostMapping("/comment")
    public IcpResponseModel<String> reportComment(@RequestBody IcpReportComment reportComment){
        return service.reportComment(reportComment);
    }

    @PostMapping("/question")
    public IcpResponseModel<String> reportQuestion(@RequestBody IcpReportQuestion reportQuestion){
        return service.reportQuestion(reportQuestion);
    }
}
