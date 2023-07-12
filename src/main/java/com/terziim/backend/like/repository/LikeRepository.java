package com.terziim.backend.like.repository;


import com.terziim.backend.like.model.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Long> {

    Likes findLikesById(Long id);


    @Query(value = "SELECT * FROM likes u WHERE u.user_id=:id limit :limit offset :offset", nativeQuery = true)
    List<Likes> getLikesByUserId(@Param("id") String id, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM likes u WHERE u.user_id=:id", nativeQuery = true)
    List<Likes> getAllLikesByUserId(@Param("id") String id);

    @Query(value = "SELECT * FROM likes u WHERE u.user_id=:id AND u.product_id=:productId", nativeQuery = true)
    Likes getLikeByUserIdAndProduct(@Param("id") String id, @Param("productId") Long productId);


    @Query(value = "SELECT * FROM likes u WHERE u.product_id=:id limit :limit offset :offset  ", nativeQuery = true)
    List<Likes> getLikesByProductId(@Param("id") Long id, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM likes u WHERE u.product_id=:id", nativeQuery = true)
    List<Likes> getLikesAllByProductId(@Param("id") Long id);

}
