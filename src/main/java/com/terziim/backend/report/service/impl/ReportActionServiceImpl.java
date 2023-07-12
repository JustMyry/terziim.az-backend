package com.terziim.backend.report.service.impl;


import com.terziim.backend.comment.model.Comment;
import com.terziim.backend.comment.service.CommentExternalService;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.report.model.ReportAction;
import com.terziim.backend.report.model.ReportList;
import com.terziim.backend.report.repository.ReportActionRepository;
import com.terziim.backend.report.service.ReportActionService;
import com.terziim.backend.report.constants.ReportConstants;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.impl.UserExternalServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Indice Inc. .
 * @target: Burda reportlar kifayet saya catdiqda gorulecek tedbirler yazilir.
 * @comments: TAM DEYIL. NOTIFICATIONLARI (HEM USER'A HEM ADMINIS. ELAVE ET) VE PRODUCT VE COMMENTIN ACTIONLARI YOXDUR HEM BIRDA HEM LISTSERVICE'DE.
 **/


@Service
public class ReportActionServiceImpl implements ReportActionService {

    private final ReportActionRepository repository;
    private final UserExternalServiceImpl userService;
    private final ProductExternalService productService;
    private final CommentExternalService commentService;
    public ReportActionServiceImpl(ReportActionRepository repository, UserExternalServiceImpl userService, ProductExternalService productService,
                                   CommentExternalService commentService){
        this.repository = repository;
        this.userService = userService;
        this.productService = productService;
        this.commentService = commentService;
    }

    @Override
    public void userReportedOnLimit(String id, String priority) {

    }

    @Override
    public void userReportedEnough(String id, ReportList  report, List<ReportList> list) {
        AppUser user = userService.findAppUserByUserId(id);
        ReportAction action = ReportAction.builder()
                .actionReason(ReportConstants.REASON_REPORTED_BY_ENOUGH_USERS)
                .reportReason(report.getReportReason())
                .whoHasBeenReported(report.getWhoHasBeenReported())
                .reportList(list)
                .type("USER")
                .isActive(true)
                .build();
        repository.save(action);
        user.setNotLocked(false);
        userService.saveUser(user);
    }


    @Override
    public void productReportedEnough(Long id, ReportList report, List<ReportList> list) {
        Product product = productService.getProductById(id);
        ReportAction action = ReportAction.builder()
                .actionReason(ReportConstants.REASON_REPORTED_BY_ENOUGH_USERS)
                .reportReason(report.getReportReason())
                .whoHasBeenReported(report.getWhoHasBeenReported())
                .reportList(list)
                .type("PRODUCT")
                .isActive(true)
                .build();
        repository.save(action);
        product.setNotLocked(false);
        productService.saveProduct(product);
    }


    @Override
    public void commentReportedEnough(Long id, ReportList report, List<ReportList> list) {
        Comment comment = commentService.getCommentById(id);
        ReportAction action = ReportAction.builder()
                .actionReason(ReportConstants.REASON_REPORTED_BY_ENOUGH_USERS)
                .reportReason(report.getReportReason())
                .whoHasBeenReported(report.getWhoHasBeenReported())
                .reportList(list)
                .type("COMMENT")
                .isActive(true)
                .build();
        commentService.deActiveComment(id);
        repository.save(action);
    }


}
