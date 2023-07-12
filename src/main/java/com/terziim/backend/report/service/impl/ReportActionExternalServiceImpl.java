package com.terziim.backend.report.service.impl;


import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.report.constants.ReportConstants;
import com.terziim.backend.report.dto.IcpReportComment;
import com.terziim.backend.report.dto.IcpReportProduct;
import com.terziim.backend.report.dto.IcpReportUser;
import com.terziim.backend.report.model.ReportAction;
import com.terziim.backend.report.model.ReportList;
import com.terziim.backend.report.repository.ReportActionRepository;
import com.terziim.backend.report.repository.ReportListRepository;
import com.terziim.backend.report.service.ReportActionExternalService;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportActionExternalServiceImpl implements ReportActionExternalService {

    private final ReportActionRepository repository;
    private final ReportListRepository listRepository;
    private final UserExternalService userService;
    private final ProductExternalService productService;
    public ReportActionExternalServiceImpl(ReportActionRepository repository, UserExternalService userService, ProductExternalService productService,
                                           ReportListRepository listRepository){
        this.repository = repository;
        this.productService = productService;
        this.userService = userService;
        this.listRepository = listRepository;
    }

    @Override
    public ReportAction getReportActionFromId(Long id) {
        return repository.findReportActionFromId(id);
    }

    @Override
    public List<ReportAction> getReportActionFromReportedIdAndType(String id, String type) {
        return repository.findReportActionFromReportedIdAndType(id, type);
    }

    @Override
    public void reportUserAsAdministrator(IcpReportUser reportUser) {
        AppUser user = userService.findAppUserByUserId(reportUser.getWhoHasBeenReported());
        ReportAction action = ReportAction.builder()
                .actionReason(ReportConstants.REASON_ADMIN_PRIORITY)
                .reportReason(reportUser.getReportReason())
                .whoHasBeenReported(reportUser.getWhoHasBeenReported())
                .reportList(null)
                .type("USER")
                .isActive(true)
                .build();
        repository.save(action);
        user.setNotLocked(false);
        userService.saveUser(user);
    }

    @Override
    public void reportCommentAsAdministrator(IcpReportComment reportComment) {

    }

    @Override
    public void reportProductAsAdministrator(IcpReportProduct reportProduct) {
        Product product = productService.getProductById(reportProduct.getWhatHasBeenReported());
        ReportAction action = ReportAction.builder()
                .actionReason(ReportConstants.REASON_ADMIN_PRIORITY)
                .reportReason(reportProduct.getReportReason())
                .whoHasBeenReported(String.valueOf(reportProduct.getWhatHasBeenReported()))
                .reportList(null)
                .type("PRODUCT")
                .isActive(true)
                .build();
        repository.save(action);
        product.setNotLocked(false);
        productService.saveProduct(product);
    }



    @Override
    public List<ReportList> getReportListsByDate(LocalDate date) {
        return listRepository.findReportListsByDate(date);
    }

    @Override
    public List<ReportList> getReportListsByPriority(LocalDate date) {
        return null;
    }

    @Override
    public List<ReportAction> getReportActionsByDate(LocalDate date) {
        return repository.findReportActionsByDate(date);
    }

    @Override
    public IcpResponseModel<List<ReportList>> reportGetListByReporterId(String id, int offset) {
        return null;
    }

    @Override
    public IcpResponseModel<List<ReportList>> reportGetAllListByActive(int offset) {
        return null;
    }

    @Override
    public IcpResponseModel<List<ReportList>> reportGetAllActionByActive(int offset) {
        return null;
    }

    @Override
    public IcpResponseModel<List<ReportList>> reportGetAllList(int offset) {
        return null;
    }

    @Override
    public IcpResponseModel<List<ReportList>> reportGetAllAction(int offset) {
        return null;
    }

    @Override
    public IcpResponseModel<List<ReportList>> reportMakeActionSeen(Long id) {

        return null;
    }
}

