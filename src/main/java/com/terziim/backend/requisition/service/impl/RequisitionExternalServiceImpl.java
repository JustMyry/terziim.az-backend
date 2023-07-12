package com.terziim.backend.requisition.service.impl;

import com.terziim.backend.requisition.dto.response.IcpBecomeRequisitonResponse;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.requisition.mapper.BecomeMapper;
import com.terziim.backend.requisition.mapper.RequisitionMapper;
import com.terziim.backend.requisition.model.BecomeRequisition;
import com.terziim.backend.requisition.model.Requisition;
import com.terziim.backend.requisition.repository.BecomeRequisitionRepository;
import com.terziim.backend.requisition.repository.RequisitionRepository;
import com.terziim.backend.requisition.service.RequisitionExternalService;
import com.terziim.backend.requisition.dto.request.IcpBecomeSellerRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RequisitionExternalServiceImpl implements RequisitionExternalService {

    private final RequisitionRepository repository;
    private final BecomeRequisitionRepository becomeRepository;
    public RequisitionExternalServiceImpl(RequisitionRepository repository, BecomeRequisitionRepository becomeRepository){
        this.repository = repository;
        this.becomeRepository = becomeRepository;
    }


    @Override
    public void createRequisition(String senderId, int catId, String requisitionHeader, String requisitionText, Boolean isActive) {
        Requisition requisition = Requisition.builder()
                .senderId(senderId)
                .requisitionCategoryId(catId)
                .requisitionHeader(requisitionHeader)
                .requisitionText(requisitionText)
                .isActive(true)
                .build();
        if(catId == 3)
            verifyBecomeSellerRequisition(senderId);
        repository.save(requisition);
    }

    @Override
    public IcpRequisitionResponse getRequisitionByIdAsResponse(Long id) {
        return RequisitionMapper.INSTANCE.getRequisitionResponse(repository.findRequisitionById(id));
    }


    @Override
    public Requisition getRequisitionById(Long id) {
        return repository.findRequisitionById(id);
    }

    @Override
    public List<IcpRequisitionResponse> getRequisitionsByCategoryId(int id, int offset) {
        return repository.findRequisitionsByCategoryWithOffset(id, offset, 40).stream().map(s->{
            return RequisitionMapper.INSTANCE.getRequisitionResponse(s);
        }).collect(Collectors.toList());
    }


    @Override
    public List<IcpRequisitionResponse> getRequisitionsBySenderId(String id, int offset) {
        return repository.findRequisitionsBySenderIdWithOffset(id, offset, 40).stream().map(s->{
            return RequisitionMapper.INSTANCE.getRequisitionResponse(s);
        }).collect(Collectors.toList());
    }

    @Override
    public List<IcpRequisitionResponse> getRequisitionsByType(int type, int offset) {
        return repository.findRequisitionsByType(type, offset, 40).stream().map(s->{
            return RequisitionMapper.INSTANCE.getRequisitionResponse(s);
        }).collect(Collectors.toList());
    }


    @Override
    public List<IcpRequisitionResponse> getRequisitionByDate(String date, int offset) {
        return repository.findRequisitionByCreatedAtWithOffset(date, offset, 40).stream().map(s->{
            return RequisitionMapper.INSTANCE.getRequisitionResponse(s);
        }).collect(Collectors.toList());
    }


    @Override
    public List<IcpRequisitionResponse> getAllActiveRequisitions(int offset) {
        return repository.findAllByActiveWithOffset(offset, 40).stream().map(s->{
            return RequisitionMapper.INSTANCE.getRequisitionResponse(s);
        }).collect(Collectors.toList());
    }


    @Override
    public List<IcpRequisitionResponse> getAllRequisitions(int offset) {
        return repository.findAllWithOffset(offset, 40).stream().map(s->{
            return RequisitionMapper.INSTANCE.getRequisitionResponse(s);
        }).collect(Collectors.toList());
    }


    @Override
    public void changeRequisitionActivity(Requisition requisition, boolean activity) {
        requisition.setActive(activity);
        repository.save(requisition);
    }

    @Override
    public void saveRequisition(Requisition requisition) {
        repository.save(requisition);
    }



    // Utility Methods

    @Override
    public void verifyBecomeSellerRequisition(String username) {
        System.out.println("-verifyBecomeSellerRequisition ================================");
        Requisition requisition = repository.findRequisitionBySenderAndType(username, 3);
        //System.out.println("/Requisition: " + requisition.toString());
        System.out.println("-verifyBecomeSellerRequisition ################################");

        if(requisition != null){
            repository.delete(requisition);
        }
    }

    @Override
    public void deleteRequisition(Requisition requisition) {
        repository.delete(requisition);
    }

    @Override
    public void createBecomeSellerRequisition(String userId, IcpBecomeSellerRequest payload) {
        BecomeRequisition requisition = becomeRepository.findBecomeRequisitionBySenderId(userId);
        if(requisition!=null)
            becomeRepository.delete(requisition);
        BecomeRequisition becomeRequisition = BecomeMapper.INSTANCE.getBecomeSellerFromRequest(payload);
        becomeRequisition.setSenderId(userId);
        becomeRepository.save(becomeRequisition);
    }

    @Override
    public List<IcpBecomeRequisitonResponse> getBecomeRequisitionByDate(String date, int offset) {
        return becomeRepository.findBecomeRequisitionByDateWithOffset(date, offset, 40).stream().map(s->{
            return BecomeMapper.INSTANCE.getBecomeReqResponse(s);
        }).collect(Collectors.toList());
    }

    @Override
    public List<IcpBecomeRequisitonResponse> getBecomeRequisitionByActve(int offset) {
        return becomeRepository.findBecomeRequisitionByActive(offset, 40).stream().map(s->{
            return BecomeMapper.INSTANCE.getBecomeReqResponse(s);
        }).collect(Collectors.toList());
    }

    @Override
    public IcpBecomeRequisitonResponse getBecomeRequisitionBySender(String userId) {
        return BecomeMapper.INSTANCE.getBecomeReqResponse(becomeRepository.findBecomeRequisitionBySenderId(userId));
    }

    @Override
    public IcpBecomeRequisitonResponse getBecomeRequisitionById(Long id) {
        return BecomeMapper.INSTANCE.getBecomeReqResponse(becomeRepository.findBecomeRequisitionById(id));
    }

    @Override
    public void changeBecomeRequisitionActivity(Long id, Boolean activity) {
        BecomeRequisition requisition = becomeRepository.findBecomeRequisitionById(id);
        requisition.setActive(activity);
        becomeRepository.save(requisition);

    }


}
