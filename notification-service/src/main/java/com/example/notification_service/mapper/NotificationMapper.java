package com.example.notification_service.mapper;


import com.example.notification_service.dto.ResponseNotification;
import com.example.notification_service.dto.RequestNotification;
import com.example.notification_service.entity.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public ResponseNotification notificationToRequestNotification(Notification notification) {
        ResponseNotification requestNotification = new ResponseNotification();
        requestNotification.setMessage(notification.getMessage());
        return requestNotification;
    }

    public Notification responseNotificationToNotification(RequestNotification responseNotification) {
        Notification notification = new Notification();
        notification.setId(responseNotification.getId());
        notification.setMessage(responseNotification.getMessage());
        notification.setTitle(responseNotification.getTitle());
        return notification;
    }
}
