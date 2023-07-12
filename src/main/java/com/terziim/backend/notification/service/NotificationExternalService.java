package com.terziim.backend.notification.service;

import com.terziim.backend.notification.model.CommentNotification;
import com.terziim.backend.notification.model.SystemNotification;

import java.util.List;

public interface NotificationExternalService {

    void createSystemNotification(String userId, String notificationName, String notificationDescription, String url);

    void createCommentNotification(String userId, String comment, Long productId, Long commentId);

    void createQuestionNotification(String buyerId, String sellerId, String sellerName, String question, Long questionId, Long productId);

    void deleteSystemNotification(Long id);

    void deleteCommentNotification(Long id);

    List<CommentNotification> showUserCommentNotifications(String userId);

    List<SystemNotification> showUserSystemNotifications(String userId);

    SystemNotification showSystemNotifications(Long id);

    CommentNotification showCommentNotifications(Long id);

}
