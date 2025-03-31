package com.biblioteca.controllers;

import com.biblioteca.dto.RatingMessage;
import com.biblioteca.producers.RatingProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
@AllArgsConstructor
@CrossOrigin
public class RatingController {

    private final RatingProducer ratingProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendRating(@RequestBody RatingMessage message) {
        ratingProducer.sendRatingEvent(message);
        return ResponseEntity.ok("Rating event sent successfully!");
    }

}
