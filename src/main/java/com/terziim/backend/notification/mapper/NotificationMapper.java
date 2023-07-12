package com.terziim.backend.notification.mapper;


import com.terziim.backend.notification.dto.response.IcpCNotificationResp;
import com.terziim.backend.notification.dto.response.IcpQNotificationResp;
import com.terziim.backend.notification.dto.response.IcpSNotificationResp;
import com.terziim.backend.notification.model.CommentNotification;
import com.terziim.backend.notification.model.QuestionNotification;
import com.terziim.backend.notification.model.SystemNotification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationMapper INSTANCE = getMapper(NotificationMapper.class);

    @Mapping(source = "id", target = "id")
    IcpCNotificationResp getCNotif(CommentNotification notification);


    @Mapping(source = "id", target = "id")
    IcpSNotificationResp getSNotif(SystemNotification notification);


    IcpQNotificationResp getQNotif(QuestionNotification notification);

}
