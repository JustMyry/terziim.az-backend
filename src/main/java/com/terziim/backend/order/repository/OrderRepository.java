package com.terziim.backend.order.repository;


import com.terziim.backend.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {

    // REST RESOURCE QUERIES ===========================================================================================
    @Query(value = "SELECT * FROM orders u WHERE u.id =:id", nativeQuery = true)
    Orders getById(@Param("id") Long id);

    @Query(value = "SELECT * FROM orders u WHERE u.id =:id AND u.is_active=true", nativeQuery = true)
    Orders getByIdActive(@Param("id") Long id);


    @Query(value = "SELECT * FROM orders u WHERE u.is_active= true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Orders> getByAllActiveWithOffset(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.offer_message_id=:offerId ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Orders> getByOfferId(@Param("offerId") Long offerId, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.accepted_by_seller= true AND u.status =\"wait\" ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Orders> getByWaitAndAcceptedBySellerWithOffset(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.buyer_id=:buyerId ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Orders> getByBuyerIdWithOffset(@Param("buyerId") String buyerId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM orders u WHERE u.buyer_id=:buyerId AND u.is_active=true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Orders> getByAcitveAndBuyerIdWithOffset(@Param("buyerId") String buyerId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM orders u WHERE u.buyer_id=:buyerId AND u.product_id=:pId ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Orders> getOrdersByBuyerIdAndProductIdWithOffset(@Param("buyerId") String buyerId, @Param("pId") Long pId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM orders u WHERE u.seller_id=:sellerId ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Orders> getBySellerIdWithOffset(@Param("sellerId") String sellerId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM orders u WHERE u.seller_id=:sellerId AND u.is_active=true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Orders> getByAcitveAnSellerIdWithOffset(@Param("sellerId") String sellerId, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.status =\"exsam\" AND u.is_active=:true ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Orders> getByStatusEXSAMWithOffset(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.status =\"wait\" AND u.is_active=true ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Orders> getByStatusWAITWithOffset(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.status =\"cargo\" AND u.is_active=true ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Orders> getByStatusCARGOWithOffset(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.status =\"cfs\" AND u.seller_id=:sellerId ORDER BY id DESC",
            nativeQuery = true)
    List<Orders> getByStatusCFSandSellerId(@Param("sellerId") String sellerId);


    @Query(value = "SELECT * FROM orders u WHERE u.seen_by_seller = false AND u.seller_id=:sellerId ORDER BY id DESC",
            nativeQuery = true)
    List<Orders> getBySeenBySellerFALSEAndSellerId(@Param("sellerId") String sellerId);


    @Query(value = "SELECT * FROM orders u WHERE u.seen_by_seller = false ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Orders> getBySeenBySellerFALSEWithOffset(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.seen_by_seller = false AND u.seller_id=:sellerId ORDER BY id DESC  limit :limit offset :offset",
            nativeQuery = true)
    List<Orders> getBySeenBySellerFALSEAndSellerIdWithOffset(@Param("sellerId") String sellerId, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM orders u WHERE u.status =\"cfb\" AND u.buyer_id=:buyerId AND u.is_active=true ORDER BY id DESC",
            nativeQuery = true)
    List<Orders> getByStatusCFBandBuyerId(@Param("buyerId") String buyerId);

}
