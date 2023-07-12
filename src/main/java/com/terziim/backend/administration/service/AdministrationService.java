package com.terziim.backend.administration.service;


import com.terziim.backend.category.dto.request.IcpCategoryRequest;
import com.terziim.backend.category.dto.request.IcpSubCategoryRequest;
import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.order.dto.response.IcpOrderResponseADMIN;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.report.dto.IcpReportResponse;
import com.terziim.backend.report.model.ReportAction;
import com.terziim.backend.report.model.ReportList;
import com.terziim.backend.requisition.dto.response.IcpBecomeRequisitonResponse;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.user.dto.request.IcpEditUserADMIN;
import com.terziim.backend.user.dto.response.IcpUserResponseADMIN;

import java.util.List;

public interface AdministrationService {
    IcpResponseModel<IcpRequisitionResponse> requisitionGetById(Long id);

    IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetBySenderId(String id, int offset);

    IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetByCategory(int id, int offset);

    IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetByDate(String gun, int offset);

    IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetAllByActive(int offset);

    IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetAll(int offset);

    IcpResponseModel<List<IcpRequisitionResponse>> requisitionsGetByType(int type, int offset);

    IcpResponseModel<String> requisitionMakeSeen(Long id);

    IcpResponseModel<IcpOrderResponseADMIN> orderGetById(Long id);

    IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByBuyerId(String id, int offset);

    IcpResponseModel<String> orderMakeSeen(Long id);

    IcpResponseModel<String> orderMakeCompleted(Long id);

    IcpResponseModel<String> orderMakeDeActive(Long id);

    IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetAll(int offset);


    IcpResponseModel<IcpUserResponseADMIN> examinationUserById(String id);

    IcpResponseModel<IcpUserResponseADMIN> examinationUserByUsername(String ad);

    IcpResponseModel<IcpUserResponseADMIN> examinationMakeUserUnBanedByUsername(String ad);

    IcpResponseModel<IcpUserResponseADMIN> examinationUserByUsernameById(String id);

    IcpResponseModel<IcpProductDetailedResponse> examinationProductById(Long id);

    IcpResponseModel<IcpProductDetailedResponse> examinationMakeProductUnbanedById(Long id);

    IcpResponseModel<IcpUserResponseADMIN> userGetByUsername(String ad);

    IcpResponseModel<IcpUserResponseADMIN> userMakeBaned(String ad);

    IcpResponseModel<IcpUserResponseADMIN> userMakeUnBaned(String ad);

    IcpResponseModel<IcpUserResponseADMIN> userEdit(String ad, IcpEditUserADMIN payload);

    IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByDoesNotSeenWithTC(int offset);

    IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByStatusWithTC(String status, int offset);

    IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetBySellerId(String id, int offset);

    IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetBySeenWithTC(int offset);

    IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByTransactionDateWithTC(String gun, int offset);


    IcpResponseModel<IcpCategoryResponse> addCategory(IcpCategoryRequest payload);

    IcpResponseModel<IcpSubCategoryResponse> addSubCategory(IcpSubCategoryRequest payload);

    IcpResponseModel<IcpCategoryResponse> editCategory(IcpCategoryRequest payload, Long id);

    IcpResponseModel<IcpSubCategoryResponse> editSubCategory(IcpSubCategoryRequest payload, Long id);

    IcpResponseModel<String> deleteCategory(Long id);

    IcpResponseModel<String> deleteSubCategory(Long id);

    IcpResponseModel<List<IcpReportResponse>> getAllReportsByDate(String gun);

    IcpResponseModel<List<IcpReportResponse>> getAllReportsByDateAndType(String gun, String type);

    IcpResponseModel<List<IcpReportResponse>> getReportFromWhoReportedAndType(String category, String userId);

    IcpResponseModel<List<IcpReportResponse>> getReportFromWhoHasBeenReportedAndType(String category, String userId);

    IcpResponseModel<String> makeReportSeen(Long reportId);

    IcpResponseModel<IcpReportResponse> getReportById(Long id);

    IcpResponseModel<IcpBecomeRequisitonResponse> becomeRequisitionGetById(Long id);

    IcpResponseModel<IcpBecomeRequisitonResponse> becomeRequisitionGetBySenderId(String userId);

    IcpResponseModel<List<IcpBecomeRequisitonResponse>> becomeRequisitionGetByActive(int offset);

    IcpResponseModel<List<IcpBecomeRequisitonResponse>> becomeRequisitionGetByDate(String gun, int offset);

    IcpResponseModel<String> becomeRequisitionChangeActivity(Long id, Boolean activity);

    IcpResponseModel<String> becomeRequisitionAccept(String userId);

    IcpResponseModel<List<IcpProductMiniResponse>> getUncheckedProducts(int offset);

    IcpResponseModel<String> setProductChecked(Long id);

    IcpResponseModel<IcpProductDetailedResponse> getProductById(Long id);
}
