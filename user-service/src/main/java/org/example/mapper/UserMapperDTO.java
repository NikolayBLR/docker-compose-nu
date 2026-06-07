package org.example.mapper;

import org.example.dto.ResponseUser;
import org.example.dto.RequestUser;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperDTO {

    public ResponseUser UserMapRequest(User user) {
        ResponseUser responseUser = new ResponseUser(user.getName(),user.getEmail());
        return responseUser;
    }

    public User UserMapResponse(RequestUser requestUser) {
        User user = new User();
        user.setName(requestUser.getName());
        user.setEmail(requestUser.getEmail());
        user.setAge(requestUser.getAge());
        return user;
    }
}
