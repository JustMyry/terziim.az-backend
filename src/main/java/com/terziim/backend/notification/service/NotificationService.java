package com.terziim.backend.notification.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.notification.dto.response.IcpCNotificationResp;
import com.terziim.backend.notification.dto.response.IcpNotificationResponse;
import com.terziim.backend.notification.dto.response.IcpQNotificationResp;
import com.terziim.backend.notification.dto.response.IcpSNotificationResp;

import java.util.List;

public interface NotificationService {
    IcpResponseModel<List<IcpNotificationResponse>> showNotificationsToUser(IcpJustJwt payload);

    IcpResponseModel<Integer> getNotificationsCount(IcpJustJwt payload);

    IcpResponseModel<List<IcpCNotificationResp>> showCommentNotifications(IcpJustJwt payload, int offset);

    IcpResponseModel<List<IcpSNotificationResp>> showSystemNotifications(IcpJustJwt payload, int offset);

    IcpResponseModel<IcpSNotificationResp> showSpecificSystemNotifications(IcpJustJwt payload, Long id);

    IcpResponseModel<IcpCNotificationResp> showSpecificCommentNotifications(IcpJustJwt payload, Long id);

    IcpResponseModel<List<IcpQNotificationResp>> showQuestionNotifications(IcpJustJwt payload, int offset);
}
