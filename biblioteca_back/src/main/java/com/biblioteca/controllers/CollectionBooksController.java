package com.biblioteca.controllers;

import com.biblioteca.models.Book;
import com.biblioteca.models.Collections;
import com.biblioteca.services.interfaces.ICollectionBooksService;
import lombok.AllArgsConstructor;
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
}
