package com.example.notification_service.service;

import com.example.notification_service.dto.KafkaDto;
import com.example.notification_service.dto.ResponseNotification;
import com.example.notification_service.dto.RequestNotification;
import com.example.notification_service.entity.Notification;
import com.example.notification_service.exception.NotificationNotFoundException;
import com.example.notification_service.mapper.NotificationMapper;
import com.example.notification_service.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AllArgsConstructor
@Service
public class NotificationService {

    private NotificationMapper notificationMapper;
    private NotificationRepository notificationRepository;
    private EmailService emailService;
    private RestTemplate restTemplate;

    public ResponseNotification createNotification(RequestNotification requestNotification) {
        Notification notification = notificationMapper.responseNotificationToNotification(requestNotification);
        Notification notification1 = notificationRepository.save(notification);
        ResponseNotification responseNotification = notificationMapper.notificationToRequestNotification(notification1);
        return responseNotification;
    }

    public ResponseNotification getByIdNotification(Integer id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
        ResponseNotification responseNotification = notificationMapper.notificationToRequestNotification(notification);
        return responseNotification;
    }

    public ResponseNotification getByTitleNotification(String title) {
        Notification notification = notificationRepository.findByTitle(title).orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
        ResponseNotification responseNotification = notificationMapper.notificationToRequestNotification(notification);
        return responseNotification;
    }

    public ResponseNotification deleteNotification(Integer id) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
        notificationRepository.delete(notification);
        ResponseNotification responseNotification = notificationMapper.notificationToRequestNotification(notification);
        return responseNotification;
    }

    public ResponseNotification updateNotification(Integer id, RequestNotification requestNotification) {
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotificationNotFoundException("Notification not found"));
        Notification notification1 = new Notification();
        notification1.setId(notification.getId());
        notification1.setTitle(requestNotification.getTitle());
        notification1.setMessage(requestNotification.getMessage());
        Notification notification2 = notificationRepository.save(notification1);
        ResponseNotification responseNotification = notificationMapper.notificationToRequestNotification(notification2);
        return responseNotification;
    }

    public String sendNotification(KafkaDto kafkaDto) {
        String email = kafkaDto.getEmail();
        String title = kafkaDto.getMessage();
        ResponseNotification responseNotification = getByTitleNotification(title);
        String message = responseNotification.getMessage();
        emailService.sendSimpleEmail(email, message);
        return "ok";
    }
}
