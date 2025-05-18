package com.biblioteca.consumers;

import com.biblioteca.dto.ReviewMessage;
import com.biblioteca.services.interfaces.IReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewConsumer {
    private static final Logger logger = LoggerFactory.getLogger(ReviewConsumer.class);

    @Autowired
    private IReviewService reviewService;

    @KafkaListener(topics = "review-result-topic", groupId = "backend-review-result")
    public void listenResult(String message) {
        logger.info("Review result received: {}", message);
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> result = mapper.readValue(message, Map.class);

            String type = (String) result.get("type");

            switch (type) {
                case "GET_ALL":
                    List<Map<String, Object>> reviewsAll = (List<Map<String, Object>>) result.get("reviews");

                    for (Map<String, Object> reviewMap : reviewsAll) {
                        ReviewMessage mess = new ReviewMessage();
                        mess.setBookId((Integer) reviewMap.get("bookId"));
                        mess.setUserId((Integer) reviewMap.get("userId"));
                        mess.setReview((String) reviewMap.get("review"));

                        reviewService.saveReview(mess);
                    }

                    logger.info("All reviews saved.");
                    break;

                case "GET_USER":
                    List<?> reviewsUser = (List<?>) result.get("reviews");
                    logger.info("User reviews received: {}", reviewsUser);
                    break;

                case "GET_COUNT":
                    Integer count = (Integer) result.get("count");
                    logger.info("Review count received: {}", count);
                    break;

                default:
                    logger.warn("Unknown message type received: {}", type);
            }

        } catch (Exception e) {
            logger.error("Error parsing review result message", e);
        }
    }


}
