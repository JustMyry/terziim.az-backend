package com.terziim.backend.conflict.service.impl;


import com.terziim.backend.conflict.dto.response.IcpConflictResponse;
import com.terziim.backend.conflict.mapper.ConflictMapper;
import com.terziim.backend.conflict.model.Conflict;
import com.terziim.backend.conflict.repository.ConflictRepository;
import com.terziim.backend.conflict.service.ConflictExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConflictExternalServiceImpl implements ConflictExternalService {


    private final ConflictRepository repository;
    public ConflictExternalServiceImpl(ConflictRepository repository){
        this.repository = repository;
    }



    @Override
    public IcpConflictResponse getById(Long id) {
        return ConflictMapper.INSTANCE.getConflictResponse(repository.getConflictById(id));
    }

    @Override
    public List<IcpConflictResponse> getActiveConflicts(int offset, int limit) {
        return getResponseList(repository.getAllWitLimits(offset, limit));
    }

    @Override
    public List<IcpConflictResponse> getByComplainantId(String userId, int offset, int limit) {
        return getResponseList(repository.getByUserIdWitLimits(userId, offset, limit));
    }

    @Override
    public List<IcpConflictResponse> getOrderId(Long orderId, int offset, int limit) {
        return getResponseList(repository.getByOrderIdWitLimits(orderId, offset, limit));
    }

    @Override
    public List<IcpConflictResponse> getAllUnSolveds(int offset, int limit) {
        return getResponseList(repository.getActiveAndNotSolvedWitLimits(offset, limit));
    }

    @Override
    public void makeAnswerToConfloct(Long id, String answer) {
        Conflict conflict = repository.getConflictById(id);
        conflict.setAnswer(answer);
        repository.save(conflict);
    }

    @Override
    public void makeConflictInActive(Long id) {
        Conflict conflict = repository.getConflictById(id);
        conflict.setActive(false);
        repository.save(conflict);
    }

    @Override
    public void setSolveToConflict(Long id, Boolean solve) {
        Conflict conflict = repository.getConflictById(id);
        conflict.setSolved(solve);
        repository.save(conflict);
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
