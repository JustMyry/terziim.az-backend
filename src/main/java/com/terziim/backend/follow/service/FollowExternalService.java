package com.terziim.backend.follow.service;


import org.springframework.stereotype.Service;

import java.util.List;

public interface FollowExternalService {

    Integer getUserFollowersCount(String userId);

    Integer getUserFollowingsCount(String userId);

    List<String> getUserFollowers(String userId);

    List<String> getUserFollowings(String userId);

    String getRelation(String followingId, String followerId);

}
