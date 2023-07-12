package com.terziim.backend.administration.resource;


import com.terziim.backend.administration.service.AdministrationService;
import com.terziim.backend.category.dto.request.IcpCategoryRequest;
import com.terziim.backend.category.dto.request.IcpSubCategoryRequest;
import com.terziim.backend.category.dto.response.IcpCategoryResponse;
import com.terziim.backend.category.dto.response.IcpSubCategoryResponse;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.order.dto.response.IcpOrderResponseADMIN;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.report.dto.IcpReportResponse;
import com.terziim.backend.requisition.dto.response.IcpBecomeRequisitonResponse;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.user.dto.request.IcpEditUserADMIN;
import com.terziim.backend.user.dto.response.IcpUserResponseADMIN;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administration")
public class AdministrationResource {

    private final AdministrationService service;
    public AdministrationResource(AdministrationService service){
        this.service = service;
    }


    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar TELEB SERVICE'e aiddir.   | +
     * */
    //// Requisition Resource ==========================================================================================
    @GetMapping("/teleb/xususi/{id}")
    public IcpResponseModel<IcpRequisitionResponse> requisitionGetById(@PathVariable Long id){
        return service.requisitionGetById(id);
    }

    @GetMapping("/teleb/istifadeci/{id}")
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetBySenderId(@PathVariable String id, @RequestParam("offset") int offset){
        return service.requisitionGetBySenderId(id, offset);
    }

    @GetMapping("/teleb/kateqoriya/{id}")
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetByCategory(@PathVariable int id, @RequestParam("offset") int offset){
        return service.requisitionGetByCategory(id, offset);
    }

    @GetMapping("/teleb/gun/{gun}")
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetByDate(@PathVariable String gun, @RequestParam("offset") int offset){
        return service.requisitionGetByDate(gun, offset);
    }

    @GetMapping("/teleb/butun/aktiv")
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetAllByActive(@RequestParam("offset") int offset){
        return service.requisitionGetAllByActive(offset);
    }

    @GetMapping("/teleb/butun")
    public IcpResponseModel<List<IcpRequisitionResponse>> requisitionGetAll(@RequestParam("offset") int offset){
        return service.requisitionGetAll(offset);
    }

    @GetMapping("/teleb/baxildi/{id}")
    public IcpResponseModel<String> requisitionMakeSeen(@PathVariable Long id){
        return service.requisitionMakeSeen(id);
    }



    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar SIFARIS SERVICE'e aiddir.   / +
     * */
    //// Order Resource ================================================================================================
    @GetMapping("/sifaris/xususi/{id}")
    public IcpResponseModel<IcpOrderResponseADMIN> orderGetById(@PathVariable Long id){
        return service.orderGetById(id);
    }

    @GetMapping("/sifaris/alici/{id}")
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByBuyerId(@PathVariable String id, @RequestParam("offset") int offset){
        return service.orderGetByBuyerId(id, offset);
    }

    @GetMapping("/sifaris/satici/{id}")
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetBySellerId(@PathVariable String id, @RequestParam("offset") int offset){
        return service.orderGetBySellerId(id, offset);
    }

    @GetMapping("/sifaris/baxildi/{id}")
    public IcpResponseModel<String> orderMakeSeen(@PathVariable Long id){
        return service.orderMakeSeen(id);
    }

    @GetMapping("/sifaris/tamamlandi/{id}")
    public IcpResponseModel<String> orderMakeCompleted(@PathVariable Long id){
        return service.orderMakeCompleted(id);
    }

    @GetMapping("/sifaris/deaktive/{id}")
    public IcpResponseModel<String> orderMakeDeActive(@PathVariable Long id){
        return service.orderMakeDeActive(id);
    }

    @GetMapping("/sifaris/butun/notseen")
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetAll(@RequestParam("offset") int offset) {
        return service.orderGetAll(offset);
    }


    @GetMapping("/sifaris/tc/notseen")
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByDoesNotSeenWithTC(@RequestParam("offset") int offset){
        return service.orderGetByDoesNotSeenWithTC(offset);
    }

    @GetMapping("/sifaris/tc/seen")
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetBySeenWithTC(@RequestParam("offset") int offset){
        return service.orderGetBySeenWithTC(offset);
    }

    @GetMapping("/sifaris/tc/status/{status}")
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByStatusWithTC(@PathVariable String status, @RequestParam("offset") int offset){
        return service.orderGetByStatusWithTC(status, offset);
    }

    @GetMapping("/sifaris/tc/emeliyyat/{gun}")
    public IcpResponseModel<List<IcpOrderResponseADMIN>> orderGetByTransactionDateWithTC(@PathVariable String gun, @RequestParam("offset") int offset){
        return service.orderGetByTransactionDateWithTC(gun, offset);
    }



    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar SIKAYET SERVICE'e aiddir.
     * */
    //// Report Resource ================================================================================================
    @GetMapping("/report/xususi")
    public IcpResponseModel<IcpReportResponse> getReportById(@RequestParam("id") Long id) {
        return service.getReportById(id);
    }

    @GetMapping("/report/gun/{gun}")
    public IcpResponseModel<List<IcpReportResponse>> getAllReportsByDate(@PathVariable String gun) {
        return service.getAllReportsByDate(gun);
    }

    @GetMapping("/report/gunvetip/{gun}")
    public IcpResponseModel<List<IcpReportResponse>> getAllReportsByDateAndType(@PathVariable String gun, @RequestParam("tip") String type) {
        return service.getAllReportsByDateAndType(gun, type);
    }

    @GetMapping("/report/eden/{category}")
    public IcpResponseModel<List<IcpReportResponse>> getReportFromWhoReportedAndType(@PathVariable String category, @RequestParam("id") String userId) {
        return service.getReportFromWhoReportedAndType(category, userId);
    }

    @GetMapping("/report/edilen/{category}")
    public IcpResponseModel<List<IcpReportResponse>> getReportFromWhoHasBeenReportedAndType(@PathVariable String category, @RequestParam("id") String userId) {
        return service.getReportFromWhoHasBeenReportedAndType(category, userId);
    }

    @GetMapping("/report/deaktiv")
    public IcpResponseModel<String> makeReportSeen(@RequestParam("id") Long reportId) {
        return service.makeReportSeen(reportId);
    }




    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar INCELEME SERVICE'e aiddir.
     * */
    //// LookUp User Resource ==========================================================================================
    @GetMapping("/inceleme/istifadeci/id/{id}")
    public IcpResponseModel<IcpUserResponseADMIN> examinationUserById(@PathVariable String id){
        return service.examinationUserById(id);
    }

    @GetMapping("/inceleme/istifadeci/ad/{ad}")
    public IcpResponseModel<IcpUserResponseADMIN> examinationUserByUsername(@PathVariable String ad){
        return service.examinationUserByUsername(ad);
    }

    @GetMapping("/inceleme/istifadeci/cixar/{ad}")
    public IcpResponseModel<IcpUserResponseADMIN> examinationMakeUserUnBanedByUsername(@PathVariable String ad){
        return service.examinationMakeUserUnBanedByUsername(ad);
    }

    @GetMapping("/inceleme/istifadeci/ad/{id}")
    public IcpResponseModel<IcpUserResponseADMIN> examinationUserByUsernameById(@PathVariable String id){
        return service.examinationUserByUsernameById(id);
    }

    @GetMapping("/inceleme/mehsul/id/{id}")
    public IcpResponseModel<IcpProductDetailedResponse> examinationProductById(@PathVariable Long id){
        return service.examinationProductById(id);
    }

    @GetMapping("/inceleme/mehsul/cixar/{id}")
    public IcpResponseModel<IcpProductDetailedResponse> examinationMakeProductUnbanedById(@PathVariable Long id){
        return service.examinationMakeProductUnbanedById(id);
    }



    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar USER SERVICE'e aiddir.
     * */
    //// User Resource ==========================================================================================
    @GetMapping("/istifadeci/xususi")
    public IcpResponseModel<IcpUserResponseADMIN> userGetByUsername(@RequestParam("ad") String ad){
        return service.userGetByUsername(ad);
    }

    @GetMapping("/istifadeci/banla")
    public IcpResponseModel<IcpUserResponseADMIN> userMakeBaned(@RequestParam("ad") String ad){
        return service.userMakeBaned(ad);
    }

    @GetMapping("/istifadeci/banac")
    public IcpResponseModel<IcpUserResponseADMIN> userMakeUnBaned(@RequestParam("ad") String ad){
        return service.userMakeUnBaned(ad);
    }

    @PostMapping("/istifadeci/duzenle")
    public IcpResponseModel<IcpUserResponseADMIN> userEdit(@RequestParam("ad") String ad, @RequestBody IcpEditUserADMIN payload){
        return service.userEdit(ad, payload);
    }




    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar SIFARIS SERVICE'e aiddir. / +
     * */
    //// Order Resource ================================================================================================

    @PostMapping("/kateqoriya/elaveet")
    public IcpResponseModel<IcpCategoryResponse> addCategory(@RequestBody IcpCategoryRequest payload){
        return service.addCategory(payload);
    }


    @PostMapping("/subkaterqoriya/elaveet")
    public IcpResponseModel<IcpSubCategoryResponse> addSubCategory(@RequestBody IcpSubCategoryRequest payload){
        return service.addSubCategory(payload);
    }


    @PostMapping("/kateqoriya/duzenle")
    public IcpResponseModel<IcpCategoryResponse> editCategory(@RequestBody IcpCategoryRequest payload, @RequestParam("id") Long id){
        return service.editCategory(payload, id);
    }


    @PostMapping("/subkaterqoriya/duzenle")
    public IcpResponseModel<IcpSubCategoryResponse> editSubCategory(@RequestBody IcpSubCategoryRequest payload, @RequestParam("id") Long id){
        return service.editSubCategory(payload, id);
    }


    @GetMapping("/kateqoriya/sil")
    public IcpResponseModel<String> deleteCategory(@RequestParam("id") Long id){
        return service.deleteCategory(id);
    }


    @GetMapping("/subkaterqoriya/sil")
    public IcpResponseModel<String> deleteSubCategory(@RequestParam("id") Long id){
        return service.deleteSubCategory(id);
    }





    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar SATICI OLMA TELEBI SERVICE'e aiddir.   / +
     * */
    //// SATICI OLMA TELEBI SERVICE ================================================================================================
    @GetMapping("/teleb/satici/xususi/{id}")
    public IcpResponseModel<IcpBecomeRequisitonResponse> becomeRequisitionGetById(@PathVariable Long id){
        return service.becomeRequisitionGetById(id);
    }

    @GetMapping("/teleb/aktivlik/satici/{id}")
    public IcpResponseModel<String> becomeRequisitionChangeActivity(@PathVariable Long id, @RequestParam("activity") Boolean activity){
        return service.becomeRequisitionChangeActivity(id, activity);
    }

    @GetMapping("/teleb/satici/istifadeci/{id}")
    public IcpResponseModel<IcpBecomeRequisitonResponse> becomeRequisitionGetBySenderId(@PathVariable String userId){
        return service.becomeRequisitionGetBySenderId(userId);
    }

    @GetMapping("/teleb/satici/butun/aktiv")
    public IcpResponseModel<List<IcpBecomeRequisitonResponse>> becomeRequisitionGetByActive(@RequestParam("offset") int offset){
        return service.becomeRequisitionGetByActive(offset);
    }

    @GetMapping("/teleb/satici/gun/{gun}")
    public IcpResponseModel<List<IcpBecomeRequisitonResponse>> becomeRequisitionGetByDate(@PathVariable String gun, @RequestParam("offset") int offset){
        return service.becomeRequisitionGetByDate(gun, offset);
    }

    @GetMapping("/teleb/qebul/satici/{userId}")
    public IcpResponseModel<String> becomeRequisitionAccept(@PathVariable String userId){
        return service.becomeRequisitionAccept(userId);
    }


    /**
     * @author: Indice Inc.
     * @target: Burdan etibaren bir sonraki bildiriye qeder butun metodlar MEHSUL SERVICE'e aiddir.   / +
     * */
    //// MEHSUL SERVICE ================================================================================================

    @GetMapping("/mehsul/goster/unchecked")
    public IcpResponseModel<List<IcpProductMiniResponse>> getUncheckedProducts(@RequestParam("offset") int offset){
        return service.getUncheckedProducts(offset);
    }


    @GetMapping("/mehsul/deyis/checked")
    public IcpResponseModel<String> setProductChecked(@RequestParam("id") Long id){
        return service.setProductChecked(id);
    }


    @GetMapping("/mehsul/goster/xususi")
    public IcpResponseModel<IcpProductDetailedResponse> getProductById(@RequestParam("id") Long id){
        return service.getProductById(id);
    }

}
