package com.terziim.backend.requisition.service;

import com.terziim.backend.requisition.dto.response.IcpBecomeRequisitonResponse;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.requisition.model.BecomeRequisition;
import com.terziim.backend.requisition.model.Requisition;
import com.terziim.backend.requisition.dto.request.IcpBecomeSellerRequest;

import java.time.LocalDate;
import java.util.List;

public interface RequisitionExternalService {

    void createRequisition(String senderId, int catId, String requisitionHeader, String requisitionText, Boolean isActive);

    IcpRequisitionResponse getRequisitionByIdAsResponse(Long id);

    Requisition getRequisitionById(Long id);

    List<IcpRequisitionResponse> getRequisitionsByCategoryId(int id, int offset);

    List<IcpRequisitionResponse> getRequisitionsBySenderId(String id, int offset);

    List<IcpRequisitionResponse> getRequisitionsByType(int type, int offset);

    List<IcpRequisitionResponse> getRequisitionByDate(String date, int offset);

    List<IcpRequisitionResponse> getAllActiveRequisitions(int offset);

    List<IcpRequisitionResponse> getAllRequisitions(int offset);

    void changeRequisitionActivity(Requisition requisition, boolean activity);

    void saveRequisition(Requisition requisition);

    void verifyBecomeSellerRequisition(String senderId);

    void deleteRequisition(Requisition requisition);

    void createBecomeSellerRequisition(String userId, IcpBecomeSellerRequest payload);

    List<IcpBecomeRequisitonResponse> getBecomeRequisitionByDate(String date, int offset);

    List<IcpBecomeRequisitonResponse> getBecomeRequisitionByActve(int offset);

    IcpBecomeRequisitonResponse getBecomeRequisitionBySender(String userId);

    IcpBecomeRequisitonResponse getBecomeRequisitionById(Long id);

    void changeBecomeRequisitionActivity(Long id, Boolean activity);
}
