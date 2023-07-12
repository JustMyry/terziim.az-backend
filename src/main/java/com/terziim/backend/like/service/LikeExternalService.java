package com.terziim.backend.like.service;


import com.terziim.backend.like.model.Likes;

import java.util.List;

public interface LikeExternalService {

    Integer likesCountOfProduct(Long productId);

    Boolean doesUserLikedProduct(String userId, Long productId);

    List<Likes> getUsersLikes(String userId);

}
