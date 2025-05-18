package com.biblioteca.producers;

import com.biblioteca.dto.ReviewMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ReviewProducer {
    private final KafkaTemplate<String, ReviewMessage> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(ReviewProducer.class);

    public void sendReviewEvent(ReviewMessage message) {
        kafkaTemplate.send("review-topic", message);
        logger.info("Sent review event: {}", message);

    }
}
