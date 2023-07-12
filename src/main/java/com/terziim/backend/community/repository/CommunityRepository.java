package com.terziim.backend.community.repository;


import com.terziim.backend.community.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {


    @Query(value = "SELECT * FROM community u WHERE u.id=:id", nativeQuery = true)
    Community findCommunityById(Long id);

    @Query(value = "SELECT * FROM community u WHERE u.author_id=:authorId AND u.is_active=true ORDER BY id DESC ", nativeQuery = true)
    List<Community> findCommunitiesByAuthorId(String authorId);

    @Query(value = "SELECT * FROM community u WHERE u.community_id=:authorId ORDER BY id DESC ", nativeQuery = true)
    List<Community> findCommunitiesByAuthorIdADMINISTRATION(String authorId);

}
