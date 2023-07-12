package com.terziim.backend.activity.service.impl;


import com.terziim.backend.activity.model.Activity;
import com.terziim.backend.activity.repository.ActivityRepository;
import com.terziim.backend.activity.service.ActivityExternalService;
import org.springframework.stereotype.Service;

import static com.terziim.backend.constants.ActivityConstants.*;

@Service
public class ActivityExternalServiceImpl implements ActivityExternalService {

    private final ActivityRepository repository;
    public ActivityExternalServiceImpl(ActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void creatActivity(String officer, Integer category, String reason, String effectedType, String effectedId) {
        Activity activity = Activity.builder()
                .officer(officer)
                .category(category)
                .reason(reason)
                .effectedType(effectedType)
                .effectedId(effectedId)
                .isActive(true)
                .build();
        repository.save(activity);
    }

    @Override
    public Activity showSpecificActionWithId(Long id) {
        return repository.searchById(id);
    }


    @Override
    public void createCFBactivity(String effectedId) {
        Activity activity = Activity.builder()
                .officer(OFFICER_ORDER_SERVICE)
                .category(CATEGORY_ORDER_LIMIT)
                .reason(REASON_OF_ORDER_LIMIT_FOR_BUYER)
                .effectedType(EFFECTED_TYPE_USER)
                .effectedId(effectedId)
                .isActive(true)
                .build();
        repository.save(activity);
    }


    @Override
    public void createCFSactivity(String effectedId) {
        Activity activity = Activity.builder()
                .officer(OFFICER_ORDER_SERVICE)
                .category(CATEGORY_ORDER_LIMIT)
                .reason(REASON_OF_ORDER_LIMIT_FOR_SELLER)
                .effectedType(EFFECTED_TYPE_USER)
                .effectedId(effectedId)
                .isActive(true)
                .build();
        repository.save(activity);
    }
}
