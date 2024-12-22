package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.models.CollectionBooks;
import com.biblioteca.models.UserBooks;
import com.biblioteca.services.interfaces.IUserBooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping(value = "api/user_books")
public class UserBooksController {

    private IUserBooksService userBooksService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserBooks(@PathVariable Integer userId, @RequestParam String status) {
        try {
            return ResponseEntity.ok(userBooksService.getBooksByStatus(userId, status));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<?> getCountUserBooks(@PathVariable Integer userId, @RequestParam String status) {
        try {
            return ResponseEntity.ok(userBooksService.getCountByStatus(userId, status));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<?> updateProgress(@PathVariable Integer bookId, @RequestParam Integer userId, @RequestParam Integer pageNum) {
        try {
            return ResponseEntity.ok(userBooksService.updateProgress(userId, bookId, pageNum));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<?> search(@PathVariable Integer userId, @RequestParam Integer type, @RequestParam String pattern) {
        try {
            return ResponseEntity.ok(userBooksService.search(userId, type, pattern));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<?> removeBook(@PathVariable Integer bookId, @RequestParam Integer userId, @RequestParam Integer type) {
        try {
            userBooksService.removeBook(bookId, userId, type);
            return ResponseEntity.ok().build();
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }
}
