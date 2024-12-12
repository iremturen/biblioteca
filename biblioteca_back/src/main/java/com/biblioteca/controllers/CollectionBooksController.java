package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.models.Book;
import com.biblioteca.models.CollectionBooks;
import com.biblioteca.models.Collections;
import com.biblioteca.services.interfaces.ICollectionBooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin()
@RestController
@RequestMapping(value = "api/collection_books")
public class CollectionBooksController {

    private ICollectionBooksService collectionBooksService;

    @GetMapping("/count/{collectionId}")
    public ResponseEntity<?> getBookCount(@PathVariable Integer collectionId) {
        try {
            return ResponseEntity.ok(collectionBooksService.getBookCount(collectionId));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/{collectionId}")
    public ResponseEntity<?> getCollectionsBooks(@PathVariable Integer collectionId, @RequestParam(required = false, defaultValue = "date") String sortBy) {
        try {
            return ResponseEntity.ok(collectionBooksService.getCollectionsBooks(collectionId, sortBy));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @GetMapping("/search/{collectionId}")
    public ResponseEntity<?> search(@PathVariable Integer collectionId, @RequestParam String pattern) {
        try {
            return ResponseEntity.ok(collectionBooksService.search(collectionId, pattern));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @GetMapping("/sortBy/{collectionId}")
    public ResponseEntity<?> sortBy(@PathVariable Integer collectionId, @RequestParam String sortBy, @RequestParam String order) {
        try {
            return ResponseEntity.ok(collectionBooksService.sortBy(collectionId, sortBy, order));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @PostMapping("/add/{collectionId}")
    public ResponseEntity<?> addBooksToCollection(@PathVariable Integer collectionId, @RequestBody List<Long> addedBooks) {
        try {
            collectionBooksService.addBooksToCollection(collectionId, addedBooks);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeBook(@RequestBody CollectionBooks collectionBooks) {
        try {
            collectionBooksService.removeBook(collectionBooks);
            return ResponseEntity.ok().build();
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

}
