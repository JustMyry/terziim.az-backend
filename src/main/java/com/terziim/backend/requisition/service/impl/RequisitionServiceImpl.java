package com.terziim.backend.requisition.service.impl;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.requisition.dto.request.IcpRequisitionRequest;
import com.terziim.backend.requisition.dto.response.IcpRequisitionResponse;
import com.terziim.backend.requisition.mapper.BecomeMapper;
import com.terziim.backend.requisition.mapper.RequisitionMapper;
import com.terziim.backend.requisition.model.BecomeRequisition;
import com.terziim.backend.requisition.model.Requisition;
import com.terziim.backend.requisition.repository.BecomeRequisitionRepository;
import com.terziim.backend.requisition.repository.RequisitionRepository;
import com.terziim.backend.requisition.service.RequisitionService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.requisition.dto.request.IcpBecomeSellerRequest;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RequisitionServiceImpl implements RequisitionService {


    private final RequisitionRepository repository;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    private final BecomeRequisitionRepository becomeRepository;
    public RequisitionServiceImpl(RequisitionRepository repository, JwtProvider jwtProvider, UserExternalService userService,
                                  BecomeRequisitionRepository becomeRepository) {
        this.repository = repository;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.becomeRepository = becomeRepository;
    }

    @Override
    public IcpResponseModel<String> createRequisition(IcpRequisitionRequest payload) {
        AppUser sender = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(sender==null || !sender.isNotLocked() || !verifyRequisitionRequest(payload.getRequisitionCategoryId()))
            return IcpResponseModel.<String>builder()
                    .response("Invalid Request")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Requisition requisition = RequisitionMapper.INSTANCE.getRequisitionFromRequest(payload);
        requisition.setSenderId(sender.getUserId());
        repository.save(requisition);
        return IcpResponseModel.<String>builder()
                .response("Ugurlu")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpRequisitionResponse>> getUsersRequisitions(IcpJustJwt payload) {
        AppUser sender = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(sender==null || !sender.isNotLocked())
            return IcpResponseModel.<List<IcpRequisitionResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<IcpRequisitionResponse> responses = repository.findRequisitionsBySenderIdWithOffset(sender.getUserId(), 0, 40).stream().map(s->{
            return RequisitionMapper.INSTANCE.getRequisitionResponse(s);
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpRequisitionResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<String> becomeSeller(IcpBecomeSellerRequest payload) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(user == null || !user.isNotLocked() || user.getUserType().equals("SELLER"))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        BecomeRequisition requisition = becomeRepository.findBecomeRequisitionBySenderId(user.getUserId());
        if(requisition!=null)
            becomeRepository.delete(requisition);
        BecomeRequisition becomeRequisition = BecomeMapper.INSTANCE.getBecomeSellerFromRequest(payload);
        becomeRequisition.setSenderId(user.getUserId());
        becomeRequisition.setActive(true);
        becomeRepository.save(becomeRequisition);
        return IcpResponseModel.<String>builder()
                .response(null)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    // Utility Methods

    private boolean verifyRequisitionRequest(int id){
        return !(id > 4 || id < 1);
    }


}
