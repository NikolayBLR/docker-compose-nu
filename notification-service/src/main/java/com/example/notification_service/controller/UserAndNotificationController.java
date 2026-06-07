package com.example.notification_service.controller;

import com.example.notification_service.dto.RequestUser;
import com.example.notification_service.dto.ResponseUser;
import com.example.notification_service.service.RestTemplateService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/microservices")
@RestController
public class UserAndNotificationController {

    private RestTemplateService restTemplateService;

    public UserAndNotificationController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @PostMapping
    public ResponseUser createUser(@Valid @RequestBody RequestUser requestUser) {
        return restTemplateService.createUser(requestUser);
    }

    @DeleteMapping("/{id}")
    public ResponseUser deleteUser(@PathVariable Integer id) {
        return restTemplateService.deleteUser(id);
    }
}
