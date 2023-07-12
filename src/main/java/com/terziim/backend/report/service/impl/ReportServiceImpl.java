package com.terziim.backend.report.service.impl;

import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.report.dto.IcpReportProduct;
import com.terziim.backend.report.dto.IcpReportQuestion;
import com.terziim.backend.report.dto.IcpReportUser;
import com.terziim.backend.report.dto.IcpReportComment;
import com.terziim.backend.report.mapper.ReportMapper;
import com.terziim.backend.report.model.Report;
import com.terziim.backend.report.repository.ReportRepository;
import com.terziim.backend.report.service.ReportService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;


@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repository;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    public ReportServiceImpl(ReportRepository repository, UserExternalService userService, JwtProvider jwtProvider){
        this.repository = repository;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }

    @Override
    public IcpResponseModel<String> reportUser(IcpReportUser report) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(report.getJwt()));
        if(user==null || !user.isNotLocked())
            return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid()).build();
        Report report1 = repository.findReportFromWhoReportedAndWhoHasBeenReportedAndType(user.getUserId(), "USER", report.getWhoHasBeenReported());
        if(report1 != null)
            if(user==null || !user.isNotLocked())
                return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid()).build();
        Report reportUser = ReportMapper.INSTANCE.getReportFromUserRequest(report);
        reportUser.setActive(true);
        reportUser.setType("USER");
        reportUser.setWhoReported(user.getUserId());
        repository.save(reportUser);
        return IcpResponseModel.<String>builder().response("OK").status(IcpResponseStatus.getSuccess()).build();
    }

    @Override
    public IcpResponseModel<String> reportProduct(IcpReportProduct report){
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(report.getJwt()));
        if(user==null || !user.isNotLocked())
            return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid()).build();
        Report report1 = repository.findReportFromWhoReportedAndWhatHasBeenReportedAndType(user.getUserId(), "PRODUCT", String.valueOf(report.getWhatHasBeenReported()));
        if(report1 != null)
            if(user==null || !user.isNotLocked())
                return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid()).build();
        Report reportUser = ReportMapper.INSTANCE.getReportFromProdRequest(report);
        reportUser.setActive(true);
        reportUser.setType("PRODUCT");
        reportUser.setWhoReported(user.getUserId());
        repository.save(reportUser);
        return IcpResponseModel.<String>builder().response("OK").status(IcpResponseStatus.getSuccess()).build();
    }

    @Override
    public IcpResponseModel<String> reportComment(IcpReportComment report) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(report.getJwt()));
        if(user==null || !user.isNotLocked())
            return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid()).build();
        Report report1 = repository.findReportFromWhoReportedAndWhatHasBeenReportedAndType(user.getUserId(), "COMMENT", String.valueOf(report.getWhatHasBeenReported()));
        if(report1 != null)
            if(user==null || !user.isNotLocked())
                return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid()).build();
        Report reportUser = ReportMapper.INSTANCE.getReportFromComRequest(report);
        reportUser.setActive(true);
        reportUser.setType("COMMENT");
        reportUser.setWhoReported(user.getUserId());
        repository.save(reportUser);
        return IcpResponseModel.<String>builder().response("OK").status(IcpResponseStatus.getSuccess()).build();
    }

    @Override
    public IcpResponseModel<String> reportQuestion(IcpReportQuestion report) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(report.getJwt()));
        if(user==null || !user.isNotLocked())
            return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid()).build();
        Report report1 = repository.findReportFromWhoReportedAndWhatHasBeenReportedAndType(user.getUserId(), "QUESTION", String.valueOf(report.getWhatHasBeenReported()));
        if(report1 != null)
            if(user==null || !user.isNotLocked())
                return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid()).build();
        Report reportUser = ReportMapper.INSTANCE.getReportFromQuesRequest(report);
        reportUser.setActive(true);
        reportUser.setType("QUESTION");
        reportUser.setWhoReported(user.getUserId());
        repository.save(reportUser);
        return IcpResponseModel.<String>builder().response("OK").status(IcpResponseStatus.getSuccess()).build();
    }



    // Utility Methods
    boolean verifyRequest(String type) {
        return type.equals("USER") || type.equals("PRODUCT") || type.equals("COMMENT") || type.equals("QUESTTION");
    }

}
