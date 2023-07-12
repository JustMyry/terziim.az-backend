package com.terziim.backend.notification.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.notification.dto.response.IcpCNotificationResp;
import com.terziim.backend.notification.dto.response.IcpNotificationResponse;
import com.terziim.backend.notification.dto.response.IcpQNotificationResp;
import com.terziim.backend.notification.dto.response.IcpSNotificationResp;
import com.terziim.backend.notification.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bildirimler")
public class NotificationResource {

    private final NotificationService service;
    public NotificationResource(NotificationService service) {
        this.service = service;
    }


    @PostMapping("/goster/test")
    public IcpResponseModel<List<IcpNotificationResponse>> showNotificationsToUser(@RequestBody IcpJustJwt payload) {
        return service.showNotificationsToUser(payload);
    }


    @PostMapping("/say")
    public IcpResponseModel<Integer> getNotificationsCount(@RequestBody IcpJustJwt payload) {
        return service.getNotificationsCount(payload);
    }


    @PostMapping("/serh")
    public IcpResponseModel<List<IcpCNotificationResp>> showCommentNotifications(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset) {
        return service.showCommentNotifications(payload, offset);
    }


    @PostMapping("/sual")
    public IcpResponseModel<List<IcpQNotificationResp>> showQuestionNotifications(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset) {
        return service.showQuestionNotifications(payload, offset);
    }


    @PostMapping("/sistem")
    public IcpResponseModel<List<IcpSNotificationResp>> showSystemNotifications(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset) {
        return service.showSystemNotifications(payload, offset);
    }


    @PostMapping("/sistem/{id}")
    public IcpResponseModel<IcpSNotificationResp> showSpecificSystemNotifications(@RequestBody IcpJustJwt payload, @PathVariable Long id) {
        return service.showSpecificSystemNotifications(payload, id);
    }


    @PostMapping("/serh/{id}")
    public IcpResponseModel<IcpCNotificationResp> showSpecificCommentNotifications(@RequestBody IcpJustJwt payload, @PathVariable Long id) {
        return service.showSpecificCommentNotifications(payload, id);
    }




}
