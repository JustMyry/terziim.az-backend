package com.terziim.backend.activity.service.impl;


import com.terziim.backend.activity.dto.response.IcpActivityResponse;
import com.terziim.backend.activity.mapper.ActivityMapper;
import com.terziim.backend.activity.model.Activity;
import com.terziim.backend.activity.repository.ActivityRepository;
import com.terziim.backend.activity.service.ActivityService;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository repository;
    public ActivityServiceImpl(ActivityRepository repository) {
        this.repository = repository;
    }


    @Override
    public IcpResponseModel<List<IcpActivityResponse>> showAllActions(int offset) {
        return getIcpActivityResponseList(repository.searchAll(offset, 40));
    }


    @Override
    public IcpResponseModel<List<IcpActivityResponse>> showAllActiveActions(int offset) {
        return getIcpActivityResponseList(repository.searchAllActive(offset, 40));
    }


    @Override
    public IcpResponseModel<IcpActivityResponse> showSpecificActionWithId(Long id) {
        Activity activity = repository.searchById(id);
        if(activity==null)
            return getRequestIsInvalid();
        return getIcpActivityResponse(activity);
    }


    @Override
    public IcpResponseModel<IcpActivityResponse> showSpecificActionWithUsername(String name) {
        return getIcpActivityResponse(repository.searchByUserId(name));
    }


    @Override
    public IcpResponseModel<String> setActionDeactive(Long id) {
        Activity activity = repository.searchById(id);
        activity.setActive(false);
        repository.save(activity);
        return getStringSuccess();
    }


    @Override
    public IcpResponseModel<String> setActionActive(Long id) {
        Activity activity = repository.searchById(id);
        activity.setActive(true);
        repository.save(activity);
        return getStringSuccess();
    }


    @Override
    public IcpResponseModel<String> deleteActionById(Long id) {
        Activity activity = repository.searchById(id);
        repository.delete(activity);
        return getStringSuccess();
    }


    //Utility methods
    IcpResponseModel<String> getStringSuccess() {
        return IcpResponseModel.<String>builder().response(null).status(IcpResponseStatus.getSuccess()).build();
    }

    IcpResponseModel<IcpActivityResponse> getRequestIsInvalid() {
        return IcpResponseModel.<IcpActivityResponse>builder().response(null).status(IcpResponseStatus.getRequestIsInvalid() ).build();
    }

    IcpResponseModel<IcpActivityResponse> getIcpActivityResponse(Activity response) {
        return IcpResponseModel.<IcpActivityResponse>builder().response(ActivityMapper.INSTANCE.getResponseFromAction(response))
                .status(IcpResponseStatus.getSuccess()).build();
    }

    IcpResponseModel<List<IcpActivityResponse>> getIcpActivityResponseList(List<Activity> response) {
        return IcpResponseModel.<List<IcpActivityResponse>>builder().response(response.stream().map(s->{
            return ActivityMapper.INSTANCE.getResponseFromAction(s);
        }).collect(Collectors.toList())).status(IcpResponseStatus.getSuccess()).build();
    }


}
