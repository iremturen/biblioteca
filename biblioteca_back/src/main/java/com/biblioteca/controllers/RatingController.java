package com.biblioteca.controllers;

import com.biblioteca.dto.RatingMessage;
import com.biblioteca.models.RatingAverage;
import com.biblioteca.producers.RatingProducer;
import com.biblioteca.services.interfaces.IRatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
@CrossOrigin
public class RatingController {

    private final RatingProducer ratingProducer;
    private final IRatingService ratingService;

    @PostMapping("/send")
    public ResponseEntity<?> sendRating(@RequestBody RatingMessage message) {
        ratingProducer.sendRatingEvent(message);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Rating event sent successfully!");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/average/{bookId}")
    public ResponseEntity<?> getAverageRating(@PathVariable Integer bookId) {
        RatingAverage ratingAverage = ratingService.getAverageRating(bookId);
        Map<String, Object> response = new HashMap<>();
        response.put("averageRating", ratingAverage.getAverageRating());
        response.put("bookId", bookId);
        return ResponseEntity.ok(response);
    }
}
