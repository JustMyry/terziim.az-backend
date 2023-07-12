package com.terziim.backend.notification.repository;


import com.terziim.backend.notification.model.QuestionNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionNotificationRepository extends JpaRepository<QuestionNotification, Long> {



    @Query(value = "SELECT * FROM question_notification u WHERE u.id=:id", nativeQuery = true)
    QuestionNotification findQNotifById(@Param("id") Long id);

    @Query(value = "SELECT * FROM question_notification u WHERE u.buyer_id=:buyerId ORDER BY id DESC", nativeQuery = true)
    List<QuestionNotification> findQNotifsByUserId(@Param("buyerId") String buyerId);

    @Query(value = "SELECT * FROM question_notification u WHERE u.buyer_id=:buyerId AND u.seen=false ORDER BY id DESC", nativeQuery = true)
    List<QuestionNotification> findQNotifsByUserIdWithNotSeen(@Param("buyerId") String buyerId);

    @Query(value = "SELECT * FROM question_notification u WHERE u.buyer_id=:buyerId AND u.seen=false ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<QuestionNotification> findQNotifsByUserIdAndNotSeenWithLimits(@Param("buyerId") String buyerId, @Param("offset") int offset, @Param("limit") int limit);


}
