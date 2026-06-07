package com.example.notification_service.controller;

import com.example.notification_service.dto.ResponseNotification;
import com.example.notification_service.dto.RequestNotification;
import com.example.notification_service.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("api/notification")
@RestController
public class NotificationController {

    private NotificationService notificationService;


    @PostMapping
    public ResponseNotification createNotification(@Valid @RequestBody RequestNotification responseNotification) {
        return notificationService.createNotification(responseNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseNotification deleteNotification(@PathVariable Integer id) {
        return notificationService.deleteNotification(id);
    }

    @GetMapping("/{id}")
    public ResponseNotification getNotification(@PathVariable Integer id) {
        return notificationService.getByIdNotification(id);
    }

    @PutMapping("/{id}")
    public ResponseNotification updateNotification(@PathVariable Integer id, @Valid @RequestBody RequestNotification responseNotification) {
        return notificationService.updateNotification(id, responseNotification);
    }
}
