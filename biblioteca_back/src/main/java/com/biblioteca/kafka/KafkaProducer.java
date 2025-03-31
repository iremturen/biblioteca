package com.biblioteca.kafka;

import com.biblioteca.dto.RatingMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, RatingMessage> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, RatingMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(RatingMessage message) {
        kafkaTemplate.send("review-topic", message);
    }
}
