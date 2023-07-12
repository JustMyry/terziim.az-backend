package com.terziim.backend.conflict.service;

import com.terziim.backend.conflict.dto.response.IcpConflictResponse;

import java.util.List;

public interface ConflictExternalService {

    IcpConflictResponse getById(Long id);

    List<IcpConflictResponse> getActiveConflicts(int offset, int limit);

    List<IcpConflictResponse> getByComplainantId(String userId, int offset, int limit);

    List<IcpConflictResponse> getOrderId(Long orderId, int offset, int limit);

    List<IcpConflictResponse> getAllUnSolveds(int offset, int limit);

    void makeAnswerToConfloct(Long id, String answer);

    void makeConflictInActive(Long id);

    void setSolveToConflict(Long id, Boolean solve);


}
