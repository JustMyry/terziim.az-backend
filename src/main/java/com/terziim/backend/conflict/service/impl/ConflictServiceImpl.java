package com.terziim.backend.conflict.service.impl;


import com.terziim.backend.conflict.dto.request.IcpConflictRequest;
import com.terziim.backend.conflict.dto.response.IcpConflictResponse;
import com.terziim.backend.conflict.mapper.ConflictMapper;
import com.terziim.backend.conflict.model.Conflict;
import com.terziim.backend.conflict.repository.ConflictRepository;
import com.terziim.backend.conflict.service.ConflictService;
import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConflictServiceImpl implements ConflictService {

    private final ConflictRepository repository;
    private final JwtProvider jwtProvider;
    private final UserExternalService userService;
    public ConflictServiceImpl(ConflictRepository repository, JwtProvider jwtProvider, UserExternalService userService) {
        this.repository = repository;
        this.jwtProvider = jwtProvider;
        this.userService = userService;
    }



    @Override
    public IcpResponseModel<String> sellerReportsConflict(IcpConflictRequest request) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(request.getJwt()));
        if(seller==null)
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Conflict conflict = ConflictMapper.INSTANCE.getConflictFromSeller(request);
        conflict.setComplainant(seller.getUserId());
        repository.save(conflict);
        return IcpResponseModel.<String>builder()
                .response("Sent Successfuly")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpConflictResponse>> showSellersConflicts(IcpJustJwt request, int offset) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(request.getJwt()));
        if(seller==null)
            return IcpResponseModel.<List<IcpConflictResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<List<IcpConflictResponse>>builder()
                .response(getResponseList(repository.getByUserIdWitLimits(seller.getUserId(), offset, 20)))
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }


    @Override
    public IcpResponseModel<String> buyerReportsConflict(IcpConflictRequest request) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(request.getJwt()));
        if(buyer==null)
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Conflict conflict = ConflictMapper.INSTANCE.getConflictFromBuyer(request);
        conflict.setComplainant(buyer.getUserId());
        repository.save(conflict);
        return IcpResponseModel.<String>builder()
                .response("Sent Successfuly")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpConflictResponse>> showBuyersConflicts(IcpJustJwt request, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(request.getJwt()));
        if(buyer==null)
            return IcpResponseModel.<List<IcpConflictResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<List<IcpConflictResponse>>builder()
                .response(getResponseList(repository.getByUserIdWitLimits(buyer.getUserId(), offset, 20)))
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }


    // Utility Methods
    List<IcpConflictResponse> getResponseList(List<Conflict> payload) {
        return payload.stream().map(s->{
            IcpConflictResponse response = ConflictMapper.INSTANCE.getConflictResponse(s);
            response.setId(s.getId());
            return response;
        }).collect(Collectors.toList());
    }
}
