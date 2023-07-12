package com.terziim.backend.notification.service.impl;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.notification.dto.response.IcpCNotificationResp;
import com.terziim.backend.notification.dto.response.IcpNotificationResponse;
import com.terziim.backend.notification.dto.response.IcpQNotificationResp;
import com.terziim.backend.notification.dto.response.IcpSNotificationResp;
import com.terziim.backend.notification.mapper.NotificationMapper;
import com.terziim.backend.notification.model.CommentNotification;
import com.terziim.backend.notification.model.QuestionNotification;
import com.terziim.backend.notification.model.SystemNotification;
import com.terziim.backend.notification.repository.CommentNotificationRepository;
import com.terziim.backend.notification.repository.QuestionNotificationRepository;
import com.terziim.backend.notification.repository.SystemNotificationRepository;
import com.terziim.backend.notification.service.NotificationService;
import com.terziim.backend.question.mapper.QuestionMapper;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class NotificationServiceImpl implements NotificationService {

    private final CommentNotificationRepository cRepository;
    private final SystemNotificationRepository sRepository;
    private final QuestionNotificationRepository qRepository;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    public NotificationServiceImpl (UserExternalService userService, JwtProvider jwtProvider,
                                    CommentNotificationRepository cRepository, SystemNotificationRepository sRepository,
                                    QuestionNotificationRepository qRepository) {
        this.jwtProvider = jwtProvider;
        this.userService = userService;
        this.sRepository = sRepository;
        this.cRepository = cRepository;
        this.qRepository = qRepository;
    }


    @Override
    public IcpResponseModel<List<IcpNotificationResponse>> showNotificationsToUser(IcpJustJwt payload) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if (user==null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpNotificationResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<CommentNotification> commentNotifications = cRepository.findCNotifsByUserId(user.getUserId());
        List<SystemNotification> systemNotifications = sRepository.findSNotifsByUserId(user.getUserId());
        List<QuestionNotification> questionNotifications = qRepository.findQNotifsByUserId(user.getUserId());

        List<IcpNotificationResponse> notifications = commentNotifications.stream().map(s->
                IcpNotificationResponse.<CommentNotification>builder().notificationType("comment").userId(user.getUserId()).t(s).build()).collect(Collectors.toList());

        notifications.addAll(systemNotifications.stream().map(s ->
                IcpNotificationResponse.<SystemNotification>builder().notificationType("system").userId(user.getUserId()).t(s).build()).toList());

        for (QuestionNotification q : questionNotifications) {
            notifications.add(
                    IcpNotificationResponse.<QuestionNotification>builder().notificationType("Question").t(q).userId(q.getBuyerId()).build()
            );
            q.setSeen(false);
            qRepository.save(q);
        }

        return IcpResponseModel.<List<IcpNotificationResponse>>builder()
                .response(notifications)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<Integer> getNotificationsCount(IcpJustJwt payload) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if (user==null || !user.isNotLocked())
            return IcpResponseModel.<Integer>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Integer commentNotifications = cRepository.findCNotifsByUserIdWithNotSeen(user.getUserId()).size();
        Integer systemNotifications = sRepository.findSNotifsByUserIdWithNotSeen(user.getUserId()).size();
        return IcpResponseModel.<Integer>builder()
                .response(commentNotifications+systemNotifications)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpCNotificationResp>> showCommentNotifications(IcpJustJwt payload, int offset) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if (user==null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpCNotificationResp>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<IcpCNotificationResp> response = cRepository.findCNotifsByUserIdAndNotSeenWithLimits(
                user.getUserId(), offset, 20).stream().map(NotificationMapper.INSTANCE::getCNotif).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpCNotificationResp>>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpSNotificationResp>> showSystemNotifications(IcpJustJwt payload, int offset) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if (user==null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpSNotificationResp>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<IcpSNotificationResp> response = sRepository.findSNotifsByUserIdAndNotSeenWithLimits(
                user.getUserId(), offset, 20).stream().map(NotificationMapper.INSTANCE::getSNotif).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpSNotificationResp>>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpQNotificationResp>> showQuestionNotifications(IcpJustJwt payload, int offset) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if (user==null || !user.isNotLocked())
            return IcpResponseModel.<List<IcpQNotificationResp>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        List<QuestionNotification> r = qRepository.findQNotifsByUserIdAndNotSeenWithLimits(user.getUserId(), offset, 20);
        List<IcpQNotificationResp> response = new ArrayList<>();
        for (QuestionNotification q : r) {
            response.add(NotificationMapper.INSTANCE.getQNotif(q));
            q.setSeen(false);
            qRepository.save(q);
        }
        return IcpResponseModel.<List<IcpQNotificationResp>>builder()
                .response(response)
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }


    @Override
    public IcpResponseModel<IcpSNotificationResp> showSpecificSystemNotifications(IcpJustJwt payload, Long id) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        SystemNotification notification = sRepository.findSNotifById(id);
        if (user==null || !user.isNotLocked() || notification==null || !notification.getUserId().equals(user.getUserId()))
            return IcpResponseModel.<IcpSNotificationResp>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        notification.setSeen(true);
        return IcpResponseModel.<IcpSNotificationResp>builder()
                .response(NotificationMapper.INSTANCE.getSNotif(notification))
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }


    @Override
    public IcpResponseModel<IcpCNotificationResp> showSpecificCommentNotifications(IcpJustJwt payload, Long id) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        CommentNotification notification = cRepository.findCNotifById(id);
        if (user==null || !user.isNotLocked() || notification==null || !notification.getUserId().equals(user.getUserId()))
            return IcpResponseModel.<IcpCNotificationResp>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        notification.setSeen(true);
        return IcpResponseModel.<IcpCNotificationResp>builder()
                .response(NotificationMapper.INSTANCE.getCNotif(notification))
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }

}
