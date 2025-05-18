package com.biblioteca.controllers;

import com.biblioteca.dto.ReviewMessage;
import com.biblioteca.producers.ReviewProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
@CrossOrigin()
public class ReviewController {
    private final ReviewProducer reviewProducer;

    @PostMapping("/send")
    public ResponseEntity<?> sendReview(@RequestBody ReviewMessage message) {
        reviewProducer.sendReviewEvent(message);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rating event sent successfully!");
        return ResponseEntity.ok(response);
    }

    
}
