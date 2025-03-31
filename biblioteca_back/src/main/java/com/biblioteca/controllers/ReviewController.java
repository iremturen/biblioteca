package com.biblioteca.controllers;

import com.biblioteca.dto.ReviewMessage;
import com.biblioteca.producers.ReviewProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
@CrossOrigin()
public class ReviewController {
    private final ReviewProducer reviewProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendReview(@RequestBody ReviewMessage message) {
        reviewProducer.sendReviewEvent(message);
        return ResponseEntity.ok("Review event sent successfully!");
    }
}
