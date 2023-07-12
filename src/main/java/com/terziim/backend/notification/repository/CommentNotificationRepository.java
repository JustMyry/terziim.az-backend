package com.terziim.backend.notification.repository;


import com.terziim.backend.notification.model.CommentNotification;
import com.terziim.backend.notification.model.QuestionNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentNotificationRepository extends JpaRepository<CommentNotification, Long> {

    @Query(value = "SELECT * FROM comment_notification u WHERE u.id=:id", nativeQuery = true)
    CommentNotification findCNotifById(@Param("id") Long id);

    @Query(value = "SELECT * FROM comment_notification u WHERE u.user_id=:userId ORDER BY id DESC", nativeQuery = true)
    List<CommentNotification> findCNotifsByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM comment_notification u WHERE u.user_id=:userId AND u.seen=false ORDER BY id DESC", nativeQuery = true)
    List<CommentNotification> findCNotifsByUserIdWithNotSeen(@Param("userId") String userId);

    @Query(value = "SELECT * FROM comment_notification u WHERE u.user_id=:userId AND u.seen=false ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<CommentNotification> findCNotifsByUserIdAndNotSeenWithLimits(@Param("userId") String userId, @Param("offset") int offset, @Param("limit") int limit);

}
