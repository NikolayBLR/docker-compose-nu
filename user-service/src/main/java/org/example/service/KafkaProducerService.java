package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.KafkaDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, KafkaDto> kafkaTemplate;

    public void sendUserEvent(KafkaDto kafkaDto) {
        kafkaTemplate.send("user-events", kafkaDto);
    }
}
