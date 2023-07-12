package com.terziim.backend.like.service.impl;


import com.terziim.backend.like.model.Likes;
import com.terziim.backend.like.repository.LikeRepository;
import com.terziim.backend.like.service.LikeExternalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeExternalServiceImpl implements LikeExternalService {

    private final LikeRepository repository;
    public LikeExternalServiceImpl(LikeRepository repository){
        this.repository = repository;
    }

    @Override
    public Integer likesCountOfProduct(Long productId) {
        return repository.getLikesAllByProductId(productId).size();
    }

    @Override
    public Boolean doesUserLikedProduct(String userId, Long productId) {
        return repository.getAllLikesByUserId(userId).stream().anyMatch(s->s.getProductId().equals(productId));
    }

    @Override
    public List<Likes> getUsersLikes(String userId) {
        return repository.getAllLikesByUserId(userId);
    }
}
