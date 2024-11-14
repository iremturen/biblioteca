package com.biblioteca.controllers;

import com.biblioteca.models.Book;
import com.biblioteca.models.CollectionBooks;
import com.biblioteca.models.Collections;
import com.biblioteca.services.interfaces.ICollectionBooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin()
@RestController
@RequestMapping(value="api/collection_books")
public class CollectionBooksController {

    private ICollectionBooksService collectionBooksService;

    @GetMapping("/count/{collectionId}")
    public ResponseEntity<Integer> getBookCount(@PathVariable Integer collectionId) {
        return ResponseEntity.ok(collectionBooksService.getBookCount(collectionId));
    }

    @GetMapping("/{collectionId}")
    public ResponseEntity<List<Book>> getCollectionsBooks(@PathVariable Integer collectionId) {
        return ResponseEntity.ok(collectionBooksService.getCollectionsBooks(collectionId));
    }

    @GetMapping("/search/{collectionId}")
    public ResponseEntity<List<Book>> search(@PathVariable  Integer collectionId, @RequestParam String pattern) {
        return ResponseEntity.ok(collectionBooksService.search(collectionId, pattern));
    }

    @GetMapping("/sortBy/{collectionId}")
    public ResponseEntity<List<Book>> sortBy(@PathVariable  Integer collectionId, @RequestParam String sortBy, @RequestParam String order ) {
        return ResponseEntity.ok(collectionBooksService.sortBy(collectionId, sortBy, order));
    }

    @PostMapping("/add/{collectionId}")
    public ResponseEntity<CollectionBooks> addBooksToCollection(@PathVariable  Integer collectionId,@RequestBody List<Long> addedBooks) {
        collectionBooksService.addBooksToCollection(collectionId, addedBooks);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
