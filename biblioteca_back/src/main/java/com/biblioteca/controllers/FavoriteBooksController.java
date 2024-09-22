package com.biblioteca.controllers;

import com.biblioteca.models.FavoriteBooks;
import com.biblioteca.models.User;
import com.biblioteca.models.UserBooks;
import com.biblioteca.services.interfaces.IFavoriteBooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping(value = "api/favorite")
@CrossOrigin()
@RestController
public class FavoriteBooksController {

    private IFavoriteBooksService favoriteBooksService;

    @GetMapping("/books/{userId}")
    public ResponseEntity<List<FavoriteBooks>> getFavorites(@PathVariable Integer userId){
        return ResponseEntity.ok(favoriteBooksService.getFavorites(userId));
    }

    @PostMapping("/remove/user/{userId}/book/{bookId}")
    public ResponseEntity<FavoriteBooks> removeFavoriteBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        favoriteBooksService.remove(userId, bookId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add/user/{userId}/book/{bookId}")
    public ResponseEntity<FavoriteBooks> addFavoriteBook(@PathVariable Integer userId, @PathVariable Integer bookId) {
        favoriteBooksService.addBook(userId, bookId);
        return ResponseEntity.ok().build();
    }

}
