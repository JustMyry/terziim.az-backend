package com.terziim.backend.follow.service.impl;

import com.terziim.backend.follow.repository.FollowRepository;
import com.terziim.backend.follow.service.FollowExternalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class FollowExternalServiceImpl implements FollowExternalService {

    private final FollowRepository repository;
    public FollowExternalServiceImpl(FollowRepository repository){
        this.repository = repository;
    }



    @Override
    public Integer getUserFollowingsCount(String userId){
        return repository.findFollowModelsByFollowerId(userId).size();
    }




    @Override
    public Integer getUserFollowersCount(String userId){
        return repository.findFollowModelsByFollowingId(userId).size();
    }




    @Override
    public List<String> getUserFollowers(String userId) {
        return repository.findFollowModelsByFollowingId(userId).stream().map(s-> {
            return s.getFollowerId();
        }).collect(Collectors.toList());
    }




    @Override
    public List<String> getUserFollowings(String userId) {
        return repository.findFollowModelsByFollowerId(userId).stream().map(s-> {
            return s.getFollowingId();
        }).collect(Collectors.toList());
    }

    @Override
    public String getRelation(String followingId, String followerId) {
        if(repository.findFollowModelsByFollowerId(followerId).stream().anyMatch(s -> s.getFollowingId().equals(followingId)))
            return "following";
        else
            return "stranger";
    }
}
