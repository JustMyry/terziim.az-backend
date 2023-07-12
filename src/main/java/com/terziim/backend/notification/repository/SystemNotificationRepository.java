package com.terziim.backend.notification.repository;

import com.terziim.backend.notification.model.QuestionNotification;
import com.terziim.backend.notification.model.SystemNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemNotificationRepository extends JpaRepository<SystemNotification, Long> {


    @Query(value = "SELECT * FROM system_notification u WHERE u.id=:id", nativeQuery = true)
    SystemNotification findSNotifById(@Param("id") Long id);

    @Query(value = "SELECT * FROM system_notification u WHERE u.user_id=:userId ORDER BY id DESC", nativeQuery = true)
    List<SystemNotification> findSNotifsByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM system_notification u WHERE u.user_id=:userId AND u.seen=false ORDER BY id DESC", nativeQuery = true)
    List<SystemNotification> findSNotifsByUserIdWithNotSeen(@Param("userId") String userId);

    @Query(value = "SELECT * FROM system_notification u WHERE u.user_id=:userId AND u.seen=false ORDER BY id DESC limit :limit offset :offset", nativeQuery = true)
    List<SystemNotification> findSNotifsByUserIdAndNotSeenWithLimits(@Param("userId") String userId, @Param("offset") int offset, @Param("limit") int limit);

}
