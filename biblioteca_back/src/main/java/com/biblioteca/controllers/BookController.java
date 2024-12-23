package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.models.Book;
import com.biblioteca.services.interfaces.IBookService;
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
@RequestMapping(value = "api/books")
public class BookController {

    private IBookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllBooks(@RequestParam(required = false) String pattern) {
        try {
            List<Book> books = bookService.getAllBooks(pattern);
            return ResponseEntity.ok(books);
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookByBookId(@PathVariable Integer bookId) {
        try {
            return ResponseEntity.ok(bookService.getBookByBookId(bookId));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/new_releases")
    public ResponseEntity<?> getNewReleases() {
        try {
            return ResponseEntity.ok(bookService.getNewReleases());
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

}
