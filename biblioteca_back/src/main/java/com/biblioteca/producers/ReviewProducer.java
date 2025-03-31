package com.biblioteca.producers;

import com.biblioteca.dto.ReviewMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewProducer {
    private final KafkaTemplate<String, ReviewMessage> kafkaTemplate;

    @Autowired
    public ReviewProducer(KafkaTemplate<String, ReviewMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendReviewEvent(ReviewMessage message) {
        kafkaTemplate.send("review-topic", message);
    }
}
