package com.terziim.backend.conflict.repository;

import com.terziim.backend.conflict.model.Conflict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConflictRepository extends JpaRepository<Conflict, Long> {

    Conflict getConflictById(Long id);



    @Query(value = "SELECT * FROM conflict u WHERE u.is_active=true ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Conflict> getAllWitLimits(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM conflict u WHERE u.is_active=true AND u.solved=false ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Conflict> getActiveAndNotSolvedWitLimits(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM conflict u WHERE u.user_type =\"BUYER\" AND u.is_active=true ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Conflict> getByWhichFromBuyersWitLimits(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM conflict u WHERE u.user_type =\"SELLER\" AND u.is_active=true ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Conflict> getByWhichFromSellersWitLimits(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM conflict u WHERE u.complainant =:complainant AND u.is_active=true ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Conflict> getByUserIdWitLimits(@Param("complainant") String complainant, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM conflict u WHERE u.category_id =:catId AND u.is_active=true ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Conflict> getByCategoryIdWitLimits(@Param("catId") Integer catId, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM conflict u WHERE u.order_id =:orderId ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Conflict> getByOrderIdWitLimits(@Param("orderId") Long orderId, @Param("offset") int offset, @Param("limit") int limit);

}
