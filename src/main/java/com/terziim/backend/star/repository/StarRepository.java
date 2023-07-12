package com.terziim.backend.star.repository;


import com.terziim.backend.star.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {

    Star findStarById(Long id);

    @Query(value = "SELECT * FROM star u WHERE u.seller_id=:sellerId AND u.buyer_id=:buyerId AND u.is_active = true", nativeQuery = true)
    Star findStarBySellerIdAndBuyerIdWithActive(@Param("sellerId") String sellerId, @Param("buyerId") String buyerId);


    @Query(value = "SELECT * FROM star u WHERE u.buyer_id=:buyerId AND u.is_active = true", nativeQuery = true)
    List<Star> findStarsByBuyerIdWithActive(@Param("buyerId") String buyerId);


    @Query(value = "SELECT * FROM star u WHERE u.seller_id=:sellerId AND u.is_active = true", nativeQuery = true)
    List<Star> findStarsBySellerIdWithActive(@Param("sellerId") String sellerId);


}
