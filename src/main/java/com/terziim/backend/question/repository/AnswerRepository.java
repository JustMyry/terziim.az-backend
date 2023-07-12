package com.terziim.backend.question.repository;

import com.terziim.backend.question.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AnswerRepository  extends JpaRepository<Answer, Long> {


    @Query(value = "SELECT * FROM answer u WHERE u.id=:id", nativeQuery = true)
    Answer getById(@Param("id") Long id);


    @Query(value = "SELECT * FROM answer u WHERE u.seller_id=:id", nativeQuery = true)
    List<Answer> getByUserId(@Param("id") String id);

}
