package com.terziim.backend.administration.service.impl;


import com.terziim.backend.administration.service.AdministrationService;
import com.terziim.backend.authority.model.Authority;
import com.terziim.backend.authority.repository.AuthRepository;
import com.terziim.backend.category.dto.request.IcpCategoryRequest;
import com.terziim.backend.category.dto.request.IcpSubCategoryRequest;
import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.category.service.CategoryExternalServicee;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.like.service.LikeExternalService;
import com.terziim.backend.order.dto.response.IcpOrderResponseADMIN;
import com.terziim.backend.order.mapper.OrderMapper;
import com.terziim.backend.order.model.Orders;
import com.terziim.backend.order.service.OrderExternalService;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.mapper.ProductMapper;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.report.dto.IcpReportResponse;
import com.terziim.backend.report.model.ReportAction;
import com.terziim.backend.report.model.ReportList;
import com.terziim.backend.report.service.ReportActionExternalService;
import com.terziim.backend.report.service.ReportExternalService;
import com.terziim.backend.requisition.dto.response.IcpBecomeRequisitonResponse;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.requisition.model.Requisition;
import com.terziim.backend.requisition.service.RequisitionExternalService;
import com.terziim.backend.user.dto.request.IcpEditUserADMIN;
import com.terziim.backend.user.dto.response.IcpUserResponseADMIN;
import com.terziim.backend.user.mapper.UserMapper;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.terziim.backend.auth.constants.SignConstantsMessage.SELLER;
import static com.terziim.backend.auth.constants.SignConstantsMessage.USER;

@Service
public class AdministrationServiceImpl implements AdministrationService {

    private final UserExternalService userService;
    private final ProductExternalService productService;
    private final CategoryExternalServicee categoryService;
    private final ReportExternalService reportService;
    private final RequisitionExternalService requisitionService;
    private final OrderExternalService orderService;
    private final LikeExternalService likeService;
    private final AuthRepository authRepository;
    public AdministrationServiceImpl(UserExternalService userService, ProductExternalService productService, ReportExternalService reportService,
                                     RequisitionExternalService requisitionService, OrderExternalService orderService, LikeExternalService likeService,
                                     CategoryExternalServicee categoryService, AuthRepository authRepository){
        this.userService = userService;
        this.productService = productService;
        this.reportService = reportService;
        this.requisitionService = requisitionService;
        this.orderService = orderService;
        this.likeService = likeService;
        this.categoryService = categoryService;
        this.authRepository = authRepository;
    }



    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar TELEB SERVICE'e aiddir.
     * */
    //// Requisition Resource ==========================================================================================
    @Override
    public IcpResponseModel<IcpRequisitionResponse> requisitionGetById(Long id) {
        return IcpResponseModel.<IcpRequisitionResponse>builder()
                .response(requisitionService.getRequisitionByIdAsResponse(id))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetBySenderId(String id, int offset) {
        return IcpResponseModel.<List<IcpRequisitionResponse>>builder()
                .response(requisitionService.getRequisitionsBySenderId(id, offset))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetByCategory(int id, int offset) {
        return IcpResponseModel.<List<IcpRequisitionResponse>>builder()
                .response(requisitionService.getRequisitionsByCategoryId(id, offset))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetByDate(String gun, int offset) {
        return IcpResponseModel.<List<IcpRequisitionResponse>>builder()
                .response(requisitionService.getRequisitionByDate(gun, offset))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetAllByActive(int offset) {
        return IcpResponseModel.<List<IcpRequisitionResponse>>builder()
                .response(requisitionService.getAllActiveRequisitions(offset))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetAll(int offset) {
        return IcpResponseModel.<List<IcpRequisitionResponse>>builder()
                .response(requisitionService.getAllRequisitions(offset))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionsGetByType(int type, int offset) {
        return IcpResponseModel.<List<IcpRequisitionResponse>>builder()
                .response(requisitionService.getRequisitionsByType(type, offset))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> requisitionMakeSeen(Long id) {
        Requisition requisition = requisitionService.getRequisitionById(id);
        requisition.setActive(false);
        requisitionService.saveRequisition(requisition);
        return IcpResponseModel.<String>builder()
                .response("Teleb artiq deAktivdir.")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }




    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar SIFARIS SERVICE'e aiddir.
     * */
    //// Order Resource ================================================================================================
    @Override
    public IcpResponseModel<IcpOrderResponseADMIN> orderGetById(Long id) {
        return  IcpResponseModel.<IcpOrderResponseADMIN>builder()
                    .response(orderService.getOrderResponse(id))
                    .status(IcpResponseStatus.getSuccess())
                    .build();
    }

    @Override
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByBuyerId(String id, int offset) {
        return IcpResponseModel.<List<IcpOrderResponseADMIN>>builder()
                .response(orderService.getOrdersByBuyerIdWithOffset(id, offset, 40))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> orderMakeSeen(Long id) {
        Orders order = orderService.getOrderById(id);
        //order.setSeen(true);
        orderService.saveOrder(order);
        return IcpResponseModel.<String>builder()
                .response("Sifaris GORULDU.")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> orderMakeCompleted(Long id) {
        Orders order = orderService.getOrderById(id);
       // order.setStatus("completed");
        orderService.saveOrder(order);
        return IcpResponseModel.<String>builder()
                .response("Sifaris BITIRILDI.")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> orderMakeDeActive(Long id) {
        Orders order = orderService.getOrderById(id);
        //order.setActive(false);
        orderService.saveOrder(order);
        return IcpResponseModel.<String>builder()
                .response("Sifaris deAktiv edildi.")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetAll(int offset) {
        return null;
//        return IcpResponseModel.<List<IcpOrderResponseADMIN>>builder()
//                .response(orderService.getAllOrdersOnDoesNotSeenWithOffset(offset, 40))
//                .status(IcpResponseStatus.getSuccess())
//                .build();
    }



    @Override
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByDoesNotSeenWithTC(int offset) {
        return null;
//        return IcpResponseModel.<List<IcpOrderResponseADMIN>>builder()
//                .response(orderService.getOrdersOnDoesNotSeenAndTerziimCargoWithOffset(offset, 40))
//                .status(IcpResponseStatus.getSuccess())
//                .build();
    }

    @Override
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetBySeenWithTC(int offset) {
        return null;
//        return IcpResponseModel.<List<IcpOrderResponseADMIN>>builder()
//                .response(orderService.getOrdersOnSeenAndTerziimCargoWithOffset(offset, 40))
//                .status(IcpResponseStatus.getSuccess())
//                .build();
    }

    @Override
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByTransactionDateWithTC(String gun, int offset) {
        return null;
//        return IcpResponseModel.<List<IcpOrderResponseADMIN>>builder()
//                .response(orderService.getOrdersOnTransactionDateAndTerziimCargoWithOffset(LocalDate.parse(gun), offset, 40))
//                .status(IcpResponseStatus.getSuccess())
//                .build();
    }


    @Override
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByStatusWithTC(String status, int offset) {
        return null;
//        return IcpResponseModel.<List<IcpOrderResponseADMIN>>builder()
//                .response(orderService.getOrdersOnStatusAndTerziimCargoWithOffset(status, offset, 40))
//                .status(IcpResponseStatus.getSuccess())
//                .build();
    }

    @Override
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetBySellerId(String id, int offset) {
        return null;
//        return IcpResponseModel.<List<IcpOrderResponseADMIN>>builder()
//                .response(orderService.getOrdersBySellerIdWithOffset(id, offset, 40))
//                .status(IcpResponseStatus.getSuccess())
//                .build();
    }



    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar SIKAYET SERVICE'e aiddir.   /
     * */
    //// Report Resource ================================================================================================
    @Override
    public IcpResponseModel<IcpReportResponse> getReportById(Long id) {
        return reportService.getReportById(id);
    }


    @Override
    public IcpResponseModel<List<IcpReportResponse>> getAllReportsByDate(String gun) {
        return reportService.getAllReportsByDate(gun);
    }

    @Override
    public IcpResponseModel<List<IcpReportResponse>> getAllReportsByDateAndType(String gun, String type) {
        return reportService.getAllReportsByDateAndType(gun, type);
    }

    @Override
    public IcpResponseModel<List<IcpReportResponse>> getReportFromWhoReportedAndType(String category, String userId) {
        return reportService.getReportFromWhoReportedAndType(category, userId);
    }

    @Override
    public IcpResponseModel<List<IcpReportResponse>> getReportFromWhoHasBeenReportedAndType(String category, String userId) {
        return reportService.getReportFromWhoHasBeenReportedAndType(category, userId);
    }

    @Override
    public IcpResponseModel<String> makeReportSeen(Long reportId) {
        return reportService.makeReportSeen(reportId);
    }



    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar INCELEME SERVICE'e aiddir.
     * */
    //// LookUp User Resource ==========================================================================================
    @Override
    public IcpResponseModel<IcpUserResponseADMIN> examinationUserById(String id) {
        AppUser user = userService.findAppUserByUserId(id);
        user.setNotLocked(false);
        userService.saveUser(user);
        return IcpResponseModel.<IcpUserResponseADMIN>builder()
                .response(UserMapper.INSTANCE.getUserResponseForAdmin(user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpUserResponseADMIN> examinationUserByUsername(String ad) {
        AppUser user = userService.findUserByUsername(ad);
        user.setNotLocked(false);
        userService.saveUser(user);
        return IcpResponseModel.<IcpUserResponseADMIN>builder()
                .response(UserMapper.INSTANCE.getUserResponseForAdmin(user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpUserResponseADMIN> examinationMakeUserUnBanedByUsername(String ad) {
        AppUser user = userService.findUserByUsername(ad);
        user.setNotLocked(true);
        userService.saveUser(user);
        return IcpResponseModel.<IcpUserResponseADMIN>builder()
                .response(UserMapper.INSTANCE.getUserResponseForAdmin(user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpUserResponseADMIN> examinationUserByUsernameById(String id) {
        AppUser user = userService.findAppUserByUserId(id);
        user.setNotLocked(true);
        userService.saveUser(user);
        return IcpResponseModel.<IcpUserResponseADMIN>builder()
                .response(UserMapper.INSTANCE.getUserResponseForAdmin(user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpProductDetailedResponse> examinationProductById(Long id) {
        Product product = productService.getProductById(id);
        product.setNotLocked(false);
        productService.saveProduct(product);
        return IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(ProductMapper.INSTANCE.getProductDetailedResponse(product))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpProductDetailedResponse> examinationMakeProductUnbanedById(Long id) {
        Product product = productService.getProductById(id);
        product.setNotLocked(true);
        productService.saveProduct(product);
        return IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(ProductMapper.INSTANCE.getProductDetailedResponse(product))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }








    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar Category SERVICE'e aiddir.
     * */
    //// Category Resource ==========================================================================================



    @Override
    public IcpResponseModel<IcpCategoryResponse> addCategory(IcpCategoryRequest payload) {
        return categoryService.addCategory(payload);
    }

    @Override
    public IcpResponseModel<IcpSubCategoryResponse> addSubCategory(IcpSubCategoryRequest payload) {
        return categoryService.addSubCategory(payload);
    }

    @Override
    public IcpResponseModel<IcpCategoryResponse> editCategory(IcpCategoryRequest payload, Long id) {
        return categoryService.editCategory(payload, id);
    }

    @Override
    public IcpResponseModel<IcpSubCategoryResponse> editSubCategory(IcpSubCategoryRequest payload, Long id) {
        return categoryService.editSubCategory(payload, id);
    }

    @Override
    public IcpResponseModel<String> deleteCategory(Long id) {
        return categoryService.deleteCategory(id);
    }

    @Override
    public IcpResponseModel<String> deleteSubCategory(Long id) {
        return categoryService.deleteSubCategory(id);
    }




    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar USER SERVICE'e aiddir.
     * */
    //// User Resource ==========================================================================================
    @Override
    public IcpResponseModel<IcpUserResponseADMIN> userGetByUsername(String ad) {
        AppUser user = userService.findUserByUsername(ad);
        return IcpResponseModel.<IcpUserResponseADMIN>builder()
                .response(UserMapper.INSTANCE.getUserResponseForAdmin(user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpUserResponseADMIN> userMakeBaned(String ad) {
        AppUser user = userService.findUserByUsername(ad);
        user.setBanned(true);
        userService.saveUser(user);
        return IcpResponseModel.<IcpUserResponseADMIN>builder()
                .response(UserMapper.INSTANCE.getUserResponseForAdmin(user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpUserResponseADMIN> userMakeUnBaned(String ad) {
        AppUser user = userService.findUserByUsername(ad);
        user.setBanned(false);
        userService.saveUser(user);
        return IcpResponseModel.<IcpUserResponseADMIN>builder()
                .response(UserMapper.INSTANCE.getUserResponseForAdmin(user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpUserResponseADMIN> userEdit(String ad, IcpEditUserADMIN payload) {
        AppUser user = userService.findUserByUsername(ad);
        UserMapper.INSTANCE.transferEditRequestToUserAsAdmin(payload, user);
        userService.saveUser(user);
        return IcpResponseModel.<IcpUserResponseADMIN>builder()
                .response(UserMapper.INSTANCE.getUserResponseForAdmin(user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar SIKAYET SERVICE'e aiddir.   /
     * */
    //// Report Resource ================================================================================================

    @Override
    public IcpResponseModel<IcpBecomeRequisitonResponse> becomeRequisitionGetById(Long id) {
        return IcpResponseModel.<IcpBecomeRequisitonResponse>builder()
                .response(requisitionService.getBecomeRequisitionById(id))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpBecomeRequisitonResponse> becomeRequisitionGetBySenderId(String userId) {
        return IcpResponseModel.<IcpBecomeRequisitonResponse>builder()
                .response(requisitionService.getBecomeRequisitionBySender(userId))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpBecomeRequisitonResponse>> becomeRequisitionGetByActive(int offset) {
        return IcpResponseModel.<List<IcpBecomeRequisitonResponse>>builder()
                .response(requisitionService.getBecomeRequisitionByActve(offset))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpBecomeRequisitonResponse>> becomeRequisitionGetByDate(String gun, int offset) {
        return IcpResponseModel.<List<IcpBecomeRequisitonResponse>>builder()
                .response(requisitionService.getBecomeRequisitionByDate(gun, offset))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> becomeRequisitionChangeActivity(Long id, Boolean activity) {
        requisitionService.changeBecomeRequisitionActivity(id, activity);
        return IcpResponseModel.<String>builder()
                .response(id +  " nomreli isteyin aktivliyi deyisdirildi -> " + activity)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> becomeRequisitionAccept(String userId) {
        AppUser user = userService.findAppUserByUserId(userId);
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authRepository.findByAuthority(SELLER));
        authorities.add(authRepository.findByAuthority(USER));
        user.setAuthorities(authorities);
        return IcpResponseModel.<String>builder()
                .response(userId +  " nomreli istifadeciye yeni rol elave edilri-> " + SELLER)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> getUncheckedProducts(int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(productService.getUncheckedProductResponse(offset, 40))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> setProductChecked(Long id) {
        productService.setProductChecked(id);
        return IcpResponseModel.<String>builder()
                .response("Success")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpProductDetailedResponse> getProductById(Long id) {
        return  IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(productService.getProductResponseWithId(id))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


}
