package com.terziim.backend.question.repository;


import com.terziim.backend.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM question u WHERE u.id=:id", nativeQuery = true)
    Question getById(@Param("id") Long id);


    @Query(value = "SELECT * FROM question u WHERE u.buyer_id=:id", nativeQuery = true)
    List<Question> getByUserId(@Param("id") String id);


    @Query(value = "SELECT * FROM question u WHERE u.buyer_id=:id AND u.answered=true ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Question> getByBuyerIdForBuyer(@Param("id") String id, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM question u WHERE u.seller_id=:id AND u.answered=false ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Question> getBySellerIdForSellerWithLimits(@Param("id") String id, @Param("offset") int offset, @Param("limit") int limit);



    @Query(value = "SELECT * FROM question u WHERE u.product_id=:id ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<Question> getByProductIdWithLimits(@Param("id") Long id, @Param("offset") int offset, @Param("limit") int limit);


}

