package com.terziim.backend.notification.service.impl;


import com.terziim.backend.notification.model.CommentNotification;
import com.terziim.backend.notification.model.QuestionNotification;
import com.terziim.backend.notification.model.SystemNotification;
import com.terziim.backend.notification.repository.CommentNotificationRepository;
import com.terziim.backend.notification.repository.QuestionNotificationRepository;
import com.terziim.backend.notification.repository.SystemNotificationRepository;
import com.terziim.backend.notification.service.NotificationExternalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationExternalServiceImpl implements NotificationExternalService {

    private final CommentNotificationRepository cRepository;
    private final SystemNotificationRepository sRepository;
    private final QuestionNotificationRepository qRepository;
    public NotificationExternalServiceImpl(CommentNotificationRepository cRepository, SystemNotificationRepository sRepository,
                                           QuestionNotificationRepository qRepository){
        this.cRepository = cRepository;
        this.sRepository = sRepository;
        this.qRepository = qRepository;
    }



    @Override
    public void createSystemNotification(String userId, String notificationName, String notificationDescription, String url) {
        SystemNotification notification = SystemNotification.builder()
                .userId(userId)
                .notificationName(notificationName)
                .notificationDescription(notificationDescription)
                .url(url)
                .seen(false)
                .build();
        sRepository.save(notification);
    }


    @Override
    public void createCommentNotification(String userId, String comment, Long productId, Long commentId) {
        CommentNotification notification = CommentNotification.builder()
                .userId(userId)
                .comment(comment)
                .productId(productId)
                .commentId(commentId)
                .seen(false)
                .build();
        cRepository.save(notification);
    }


    @Override
    public void createQuestionNotification(String buyerId, String sellerId, String sellerName, String question, Long questionId, Long productId) {
        QuestionNotification notification = QuestionNotification.builder()
                .buyerId(buyerId)
                .questionId(questionId)
                .question(question)
                .sellerId(sellerId)
                .sellerName(sellerName)
                .productId(productId)
                .build();
        qRepository.save(notification);
    }

    @Override
    public void deleteSystemNotification(Long id) {
        sRepository.delete(sRepository.findSNotifById(id));
    }

    @Override
    public void deleteCommentNotification(Long id) {
        cRepository.delete(cRepository.findCNotifById(id));
    }

    @Override
    public List<CommentNotification> showUserCommentNotifications(String userId) {
        return cRepository.findCNotifsByUserId(userId);
    }

    @Override
    public List<SystemNotification> showUserSystemNotifications(String userId) {
        return sRepository.findSNotifsByUserId(userId);
    }

    @Override
    public SystemNotification showSystemNotifications(Long id) {
        return sRepository.findSNotifById(id);
    }

    @Override
    public CommentNotification showCommentNotifications(Long id) {
        return cRepository.findCNotifById(id);
    }


}
