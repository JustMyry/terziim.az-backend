package com.terziim.backend.tekkart.repository;

import com.terziim.backend.tekkart.model.TekKart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TekKartRepository extends JpaRepository<TekKart, Long> {

    TekKart findTekKartById(Long id);


    @Query(value = "SELECT * FROM tek_kart u WHERE u.seller_id =:SellerId AND u.is_active =true ORDER BY id DESC  limit :limit offset :offset", nativeQuery = true)
    public List<TekKart> findTekKartBySellerId(@Param("SellerId") String SellerId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM tek_kart u WHERE u.seller_id =:SellerId ORDER BY id DESC  limit :limit offset :offset", nativeQuery = true)
    public List<TekKart> findTekKartBySellerIdWithoutActive(@Param("SellerId") String SellerId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM tek_kart u WHERE u.buyer_id =:BuyerId AND u.is_active =true ORDER BY id DESC  limit :limit offset :offset", nativeQuery = true)
    public List<TekKart> findTekKartByBuyerId(@Param("BuyerId") String BuyerId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM tek_kart u WHERE u.buyer_id =:BuyerId ORDER BY id DESC  limit :limit offset :offset", nativeQuery = true)
    public List<TekKart> findTekKartByBuyerIdWithoutActive(@Param("BuyerId") String BuyerId, @Param("offset") int offset, @Param("limit") int limit);

}
