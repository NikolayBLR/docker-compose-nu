package com.example.notification_service.service;

import com.example.notification_service.dto.KafkaDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Setter
@Getter
@Service
public class KafkaUserAndNotificationService {

    private NotificationService notificationService;

    @KafkaListener(topics = "user-events")
    public void consume(@Valid KafkaDto kafkaDto) {
        notificationService.sendNotification(kafkaDto);
    }
}
