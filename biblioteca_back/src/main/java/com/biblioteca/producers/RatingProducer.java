package com.biblioteca.producers;

import com.biblioteca.dto.RatingMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RatingProducer {
    private static final Logger logger = LoggerFactory.getLogger(RatingProducer.class);
    private final KafkaTemplate<String, RatingMessage> kafkaTemplate;

    public void sendRatingEvent(RatingMessage message) {
        kafkaTemplate.send("rating-topic", message);
        logger.info("Sent rating event: {}", message);
    }
}
