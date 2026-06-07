package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ResponseUser {

    private String name;
    private String email;

    public ResponseUser(String name) {
        this.name = name;
    }
}




