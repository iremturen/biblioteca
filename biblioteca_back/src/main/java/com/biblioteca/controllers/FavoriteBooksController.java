package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.services.interfaces.IFavoriteBooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@RequestMapping(value = "api/favorite")
@CrossOrigin()
@RestController
public class FavoriteBooksController {

    private IFavoriteBooksService favoriteBooksService;

    @GetMapping("/books/{userId}")
    public ResponseEntity<?> getFavorites(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(favoriteBooksService.getFavorites(userId));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @DeleteMapping("/remove/user/{userId}/book/{bookId}")
    public ResponseEntity<?> removeFavoriteBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        try {
            favoriteBooksService.remove(userId, bookId);
            return ResponseEntity.ok().build();
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PostMapping("/add/user/{userId}/book/{bookId}")
    public ResponseEntity<?> addFavoriteBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        try {
            favoriteBooksService.addBook(userId, bookId);
            return ResponseEntity.ok().build();
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> isFavorite(@PathVariable Integer bookId, @RequestParam Integer userId) {
        try {
            return ResponseEntity.ok(favoriteBooksService.isFavorite(bookId, userId));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

}
