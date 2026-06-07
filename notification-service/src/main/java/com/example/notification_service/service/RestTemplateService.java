package com.example.notification_service.service;

import com.example.notification_service.dto.RequestUser;
import com.example.notification_service.dto.ResponseNotification;
import com.example.notification_service.dto.ResponseUser;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RestTemplateService {

    private final String POST = "создание";
    private final String DELETE = "удаление";
    private NotificationService notificationService;
    private RestTemplate restTemplate;
    private EmailService emailService;

    public RestTemplateService(NotificationService notificationService, RestTemplate restTemplate, EmailService emailService) {
        this.notificationService = notificationService;
        this.restTemplate = restTemplate;
        this.emailService = emailService;
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackCreateUser")
    public ResponseUser createUser(RequestUser requestUser) {
        var user = restTemplate.postForObject("lb://USER-SERVICE-SWAGGER/api/user", requestUser, ResponseUser.class);
        ResponseNotification notification = notificationService.getByTitleNotification(POST);
        emailService.sendSimpleEmail(user.getEmail(), notification.getMessage());
        return user;
    }

    public ResponseUser fallbackCreateUser(RequestUser requestUser, Throwable t) {
        log.error(t.getMessage());
        return new ResponseUser("---", "@mail.com");
    }

    @CircuitBreaker(name = "userService", fallbackMethod = "fallbackDeleteUser")
    public ResponseUser deleteUser(Integer id) {
        var user = restTemplate.getForObject("lb://USER-SERVICE-SWAGGER/api/user/" + id, ResponseUser.class);
        ResponseNotification notification = notificationService.getByTitleNotification(DELETE);
        restTemplate.delete("lb://USER-SERVICE-SWAGGER/api/user/" + id);
        emailService.sendSimpleEmail(user.getEmail(), notification.getMessage());
        return user;
    }

    public ResponseUser fallbackDeleteUser(Integer id, Throwable t) {
        log.error(t.getMessage());
        return new ResponseUser("---", "mail");
    }

}
