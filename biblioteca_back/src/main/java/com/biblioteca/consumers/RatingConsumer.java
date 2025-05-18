package com.biblioteca.consumers;

import com.biblioteca.services.RatingService;
import com.biblioteca.services.interfaces.IRatingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RatingConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RatingConsumer.class);

    @Autowired
    private IRatingService ratingService;

    @KafkaListener(topics = "rating-result-topic", groupId = "backend-rating-result")
    public void listenResult(String message) {
        logger.info("Rating result received: {}", message);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> result = mapper.readValue(message, Map.class);

            String type = (String) result.get("type");

            switch (type) {
                case "GET_ALL":
                    List<?> ratingsAll = (List<?>) result.get("ratings");
                    logger.info("All ratings received: {}", ratingsAll);
                    break;

                case "GET_USER":
                    List<?> ratingsUser = (List<?>) result.get("ratings");
                    logger.info("User ratings received: {}", ratingsUser);
                    break;

                case "GET_COUNT":
                    Integer count = (Integer) result.get("count");
                    logger.info("Rating count received: {}", count);
                    break;

                case "GET_AVERAGE":
                    Double average = (Double) result.get("averageRating");
                    Integer bookId = (Integer) result.get("bookId");
                    ratingService.saveAverage(average,bookId);
                    logger.info("Average rating is: {}", average);
                    break;

                default:
                    logger.warn("Unknown message type received: {}", type);
            }

        } catch (Exception e) {
            logger.error("Error parsing rating result message", e);
        }
    }

}
