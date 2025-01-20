package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.services.interfaces.IRatingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping(value = "api/rate")
public class RatingController {

    private IRatingService ratingService;

    @GetMapping("/average")
    public ResponseEntity<?> getBookAverageRating(@RequestParam Integer bookId) {
        try {
            return ResponseEntity.ok(ratingService.getAverageRating(bookId));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRating(@RequestParam Integer bookId, @RequestParam Integer userId, @RequestParam Integer rating) {
        try {
            ratingService.saveRating(bookId, userId, rating);
            return ResponseEntity.ok().build();
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    }

