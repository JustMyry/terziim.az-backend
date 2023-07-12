package com.terziim.backend.report.service.impl;

import com.terziim.backend.comment.model.Comment;
import com.terziim.backend.comment.service.CommentExternalService;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.report.dto.IcpReportProduct;
import com.terziim.backend.report.dto.IcpReportUser;
import com.terziim.backend.report.model.ReportList;
import com.terziim.backend.report.dto.IcpReportComment;
import com.terziim.backend.report.repository.ReportListRepository;
import com.terziim.backend.report.service.ReportListService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.impl.UserExternalServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReportListServiceImpl implements ReportListService {

    private final ReportListRepository reportListRepository;
    private final ReportActionServiceImpl reportActionService;
    private final UserExternalServiceImpl userService;
    private final JwtProvider jwtProvider;
    private final ProductExternalService productService;
    private final CommentExternalService commentService;
    public ReportListServiceImpl(ReportListRepository reportListRepository, ReportActionServiceImpl reportActionService, UserExternalServiceImpl userService,
                                 JwtProvider jwtProvider, ProductExternalService productService, CommentExternalService commentService){
        this.jwtProvider = jwtProvider;
        this.reportListRepository = reportListRepository;
        this.reportActionService = reportActionService;
        this.userService = userService;
        this.productService = productService;
        this.commentService = commentService;
    }


    @Override
    public IcpResponseModel<String> reportUser(IcpReportUser reportUser) {
        AppUser reporter = userService.findAppUserByUserId(jwtProvider.getSubject(reportUser.getJwt()));
        AppUser reportedUser = userService.findAppUserByUserId(reportUser.getWhoHasBeenReported());
        if(reporter==null || reportedUser==null|| !reportedUser.isNotLocked() || !reporter.isNotLocked())
                return  IcpResponseModel.<String>builder()
                            .response("Invalid Request")
                            .status(IcpResponseStatus.getRequestIsInvalid())
                            .build();
        List<ReportList> reportTest = reportListRepository.findReportListsWithUsersAndActive(reportedUser.getUserId(), reporter.getUserId());
        if( reportTest.size() > 0) {
            if (((new Date()).getTime() - reportTest.get(reportTest.size() - 1).getCreatedAt().getTime() < 10000)) {
                return IcpResponseModel.<String>builder()
                        .response("You have already reported the user")
                        .status(IcpResponseStatus.getRequestIsInvalid())
                        .build();
            }
        }
        ReportList reportList = ReportList.builder()
                .isActive(true)
                .priority("USER")
                .type("USER")
                .reportReason(reportUser.getReportReason())
                .whoHasBeenReported(reportUser.getWhoHasBeenReported())
                .whoReported(reporter.getUserId())
                .build();
        reportListRepository.save(reportList);
        checkUserReports(reportedUser.getUserId(), reportList);
        return IcpResponseModel.<String>builder()
                .response("User Reported")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    private void checkUserReports(String id, ReportList list) {
        List<ReportList> reportList = reportListRepository.findReportListsByIdAndType(id, "USER");
        if(reportList.size() == 40 ){
            reportActionService.userReportedOnLimit(id, list.getPriority());
        }
        else if(reportList.size() >= 50){
            reportActionService.userReportedEnough(id, list, reportList);
        }
    }






    @Override
    public IcpResponseModel<String> reportProduct(IcpReportProduct reportProduct){
        Product product = productService.getProductById(reportProduct.getWhatHasBeenReported());
        AppUser reporter = userService.findAppUserByUserId(jwtProvider.getSubject(reportProduct.getJwt()));
        if(reporter==null || !reporter.isNotLocked() || product==null)
            IcpResponseModel.<String>builder()
                    .response("Invalid Request")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<ReportList> reportTest = reportListRepository.findReportListsWithUsersAndActive(String.valueOf(product.getId()), reporter.getUserId());
        if( reportTest.size() > 0) {
            if (((new Date()).getTime() - reportTest.get(reportTest.size() - 1).getCreatedAt().getTime() < 10000)) {
                return IcpResponseModel.<String>builder()
                        .response("You have already reported the user")
                        .status(IcpResponseStatus.getRequestIsInvalid())
                        .build();
            }
        }
        ReportList reportList = ReportList.builder()
                .isActive(true)
                .priority("USER")
                .type("PRODUCT")
                .reportReason(reportProduct.getReportReason())
                .whoHasBeenReported(String.valueOf(reportProduct.getWhatHasBeenReported()))
                .whoReported(reporter.getUserId())
                .build();
        reportListRepository.save(reportList);
        checkProductReports(reportProduct.getWhatHasBeenReported(), reportList);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    private void checkProductReports(Long id, ReportList list) {
        List<ReportList> reportList = reportListRepository.findReportListsByIdAndType(String.valueOf(id), "PRODUCT");
        if(reportList.size() >= 20)
            reportActionService.productReportedEnough(id, list, reportList);
    }







    @Override
    public IcpResponseModel<String> reportComment(IcpReportComment reportComment){
        Comment comment = commentService.getCommentById(reportComment.getWhatHasBeenReported());
        AppUser reporter = userService.findAppUserByUserId(jwtProvider.getSubject(reportComment.getJwt()));
        if(reporter==null || !reporter.isNotLocked() || comment==null)
            IcpResponseModel.<String>builder()
                    .response("Invalid Request")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();

        List<ReportList> reportTest = reportListRepository.findReportListsWithUsersAndActive(String.valueOf(comment.getId()), reporter.getUserId());
        if( reportTest.size() > 0) {
            if (((new Date()).getTime() - reportTest.get(reportTest.size() - 1).getCreatedAt().getTime() < 10000)) {
                return IcpResponseModel.<String>builder()
                        .response("You have already reported the user")
                        .status(IcpResponseStatus.getRequestIsInvalid())
                        .build();
            }
        }
        ReportList reportList = ReportList.builder()
                .isActive(true)
                .priority("USER")
                .type("COMMENT")
                .reportReason(reportComment.getReportReason())
                .whoHasBeenReported(String.valueOf(reportComment.getWhatHasBeenReported()))
                .whoReported(reporter.getUserId())
                .build();
        reportListRepository.save(reportList);
        checkCommentsersReports(reportComment.getWhatHasBeenReported(), reportList);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    private void checkCommentsersReports(Long id, ReportList list) {
        List<ReportList> reportList = reportListRepository.findReportListsByIdAndType(String.valueOf(id), "COMMENT");
        if(reportList.size() >= 10){
            reportActionService.commentReportedEnough(id, list, reportList);
        }
    }

}
