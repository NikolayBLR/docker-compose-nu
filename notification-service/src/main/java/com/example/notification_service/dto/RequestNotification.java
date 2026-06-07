package com.example.notification_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestNotification {
    @Null
    private Integer id;
    @NotNull
    private String title;
    @NotNull
    private String message;
}
