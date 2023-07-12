package com.terziim.backend.activity.repository;


import com.terziim.backend.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = "SELECT * FROM activity u WHERE u.id =:id", nativeQuery = true)
    Activity searchById(@Param("id") Long id);


    @Query(value = "SELECT * FROM activity u WHERE u.is_active = true AND u.category =:category ORDER BY u.created_at DESC  limit :limit offset :offset  ", nativeQuery = true)
    List<Activity> searchOnCategory(@Param("category") String category, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM activity u ORDER BY u.created_at DESC  limit :limit offset :offset  ", nativeQuery = true)
    List<Activity> searchAll(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM activity u WHERE u.is_active = true ORDER BY u.created_at DESC  limit :limit offset :offset  ", nativeQuery = true)
    List<Activity> searchAllActive(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM activity u WHERE u.effected_id =:userId AND u.effected_type = \"USER\" ORDER BY u.created_at DESC", nativeQuery = true)
    Activity searchByUserId(@Param("userId") String userId);
}
