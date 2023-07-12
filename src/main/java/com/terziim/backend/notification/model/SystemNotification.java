package com.terziim.backend.notification.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.Builder;

import java.util.Date;

@Entity
@Builder
public class SystemNotification extends BaseModel {

    private String userId;

    private String notificationName;
    private String notificationDescription;
    private boolean seen;

    private String url;


    //Constructors
    public SystemNotification() {}

    public SystemNotification(Long id, Date createdAt, Date updatedAt, String userId, String notificationName, String notificationDescription, boolean seen, String url) {
        super(id, createdAt, updatedAt);
        this.userId = userId;
        this.notificationName = notificationName;
        this.notificationDescription = notificationDescription;
        this.seen = seen;
        this.url = url;
    }

    public SystemNotification(String userId, String notificationName, String notificationDescription, boolean seen, String url) {
        this.userId = userId;
        this.notificationName = notificationName;
        this.notificationDescription = notificationDescription;
        this.seen = seen;
        this.url = url;
    }

    //Getters and Setters
    public String getNotificationName() {
        return notificationName;
    }

    public void setNotificationName(String notificationName) {
        this.notificationName = notificationName;
    }

    public String getNotificationDescription() {
        return notificationDescription;
    }

    public void setNotificationDescription(String notificationDescription) {
        this.notificationDescription = notificationDescription;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
