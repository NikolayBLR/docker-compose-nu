package org.example.service;

import org.example.dto.KafkaDto;
import org.example.dto.ResponseUser;
import org.example.dto.RequestUser;
import org.example.entity.User;
import org.example.exception.UserNotFoundException;
import org.example.mapper.UserMapperDTO;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final String CREATE = "создание";
    private static final String DELETE = "удаление";
    private final UserRepository userRepository;
    private final UserMapperDTO userMapperDTO;
    private final KafkaProducerService kafkaProducerService;

    public UserService(UserRepository userRepository, UserMapperDTO userMapperDTO, KafkaProducerService kafkaProducerService) {
        this.userRepository = userRepository;
        this.userMapperDTO = userMapperDTO;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Transactional
    public ResponseUser saveUser(RequestUser requestUser) {
        User user1 = userMapperDTO.UserMapResponse(requestUser);
        User user2 = userRepository.save(user1);
        KafkaDto kafkaDto = new KafkaDto();
        kafkaDto.setEmail(user2.getEmail());
        kafkaDto.setMessage(CREATE);
        kafkaProducerService.sendUserEvent(kafkaDto);
        ResponseUser responseUser = userMapperDTO.UserMapRequest(user2);
        return responseUser;
    }

    @Transactional
    public ResponseUser getUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Not found User"));
        ResponseUser responseUser = userMapperDTO.UserMapRequest(user);
        return responseUser;
    }

    @Transactional
    public ResponseUser deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Not found User"));
        userRepository.delete(user);
        KafkaDto kafkaDto = new KafkaDto();
        kafkaDto.setEmail(user.getEmail());
        kafkaDto.setMessage(DELETE);
        ResponseUser responseUser = userMapperDTO.UserMapRequest(user);
        return responseUser;
    }

    @Transactional
    public ResponseUser updateUser(Integer id, RequestUser user) {
        User user2 = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Not found User"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setAge(user.getAge());
        user2.setId(user2.getId());
        userRepository.save(user2);
        ResponseUser responseUser = userMapperDTO.UserMapRequest(user2);
        return responseUser;
    }
}
