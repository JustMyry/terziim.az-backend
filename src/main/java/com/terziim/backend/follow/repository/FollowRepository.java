package com.terziim.backend.follow.repository;


import com.terziim.backend.follow.model.FollowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<FollowModel, Long> {

    FollowModel findFollowTableById(String id);


    @Query(value = "SELECT * FROM follow_model u WHERE u.follower_id=:followerId AND u.following_id=:followingId", nativeQuery = true)
    FollowModel findFollowTableByFollowerAndFollowing(String followerId, String followingId);


    @Query(value = "SELECT * FROM follow_model u WHERE u.follower_id=:followerId AND u.is_active = true ORDER BY id DESC ", nativeQuery = true)
    List<FollowModel> findFollowModelsByFollowerId(String followerId);


    @Query(value = "SELECT * FROM follow_model u WHERE u.following_id=:followingId AND u.is_active = true ORDER BY id DESC ", nativeQuery = true)
    List<FollowModel> findFollowModelsByFollowingId(String followingId);


    @Query(value = "SELECT * FROM follow_model u WHERE u.follower_id=:followerId AND u.is_active = true ORDER BY id DESC  limit :limit offset :offset ", nativeQuery = true)
    List<FollowModel> findFollowTablesByFollowerWithLO(@Param("followerId") String userId, @Param("limit") int limit, @Param("offset") int offset);


    @Query(value = "SELECT * FROM follow_model u WHERE u.following_id=:following AND u.is_active = true ORDER BY id DESC  limit :limit offset :offset ", nativeQuery = true)
    List<FollowModel> findFollowTablesByFollowingWithLO(@Param("following") String userId, @Param("limit") int limit, @Param("offset") int offset);

}
