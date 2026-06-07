package org.example.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class RequestUser {
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private Integer age;

}

