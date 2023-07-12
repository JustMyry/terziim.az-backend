package com.terziim.backend.activity.service;

import com.terziim.backend.activity.model.Activity;

public interface ActivityExternalService {

    void creatActivity(String officer, Integer category, String reason, String effectedType, String effectedId);

    Activity showSpecificActionWithId(Long id);

    void createCFBactivity(String effectedId);

    void createCFSactivity(String effectedId);

}
