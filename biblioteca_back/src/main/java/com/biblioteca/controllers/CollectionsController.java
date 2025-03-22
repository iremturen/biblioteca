package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.models.Collections;
import com.biblioteca.services.interfaces.ICollectionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin()
@RestController
@RequestMapping(value = "api/collections")
public class CollectionsController {

    private ICollectionsService collectionsService;

    @GetMapping("/{collectionId}")
    public ResponseEntity<?> getCollectionById(@PathVariable Integer collectionId) {
        try {
            return ResponseEntity.ok(collectionsService.getCollectionById(collectionId));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @GetMapping("/user_collection/{userId}")
    public ResponseEntity<?> getCollectionsByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(collectionsService.getCollectionsByUserId(userId));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PutMapping("/update/{collectionId}")
    public ResponseEntity<?> update(@PathVariable Integer collectionId, @RequestBody Collections collections) {
        try {
            return ResponseEntity.ok(collectionsService.update(collectionId, collections));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Collections collections) {
        try {
            Collections savedCollection = collectionsService.create(collections);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCollection);
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

    @DeleteMapping("/{collectionId}")
    public ResponseEntity<?> delete(@PathVariable Integer collectionId) {
        try {
            collectionsService.delete(collectionId);
            return ResponseEntity.ok().build();
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }
    }

}
