package com.biblioteca.controllers;

import com.biblioteca.models.Book;
import com.biblioteca.models.Collections;
import com.biblioteca.models.User;
import com.biblioteca.services.interfaces.ICollectionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin()
@RestController
@RequestMapping(value = "api/collections")
public class CollectionsController {

    private ICollectionsService collectionsService;

    @GetMapping("/{collectionId}")
    public ResponseEntity<Collections> getCollecitonById(@PathVariable Integer collectionId) {
        return ResponseEntity.ok(collectionsService.getCollecitonById(collectionId));
    }

    @GetMapping("/user_collection/{userId}")
    public ResponseEntity<List<Collections>> getCollecitonsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(collectionsService.getCollecitonsByUserId(userId));
    }

    @PutMapping("/update/{collectionId}")
    public ResponseEntity<Collections> update(@PathVariable Integer collectionId,  @RequestBody Collections collections) {
        return ResponseEntity.ok(collectionsService.update(collectionId, collections));
    }

    @PostMapping("")
    public ResponseEntity<Collections> create(@RequestBody Collections collections) {
        Collections savedCollection = collectionsService.create(collections);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCollection);
    }

    @DeleteMapping("/{collectionId}")
    public ResponseEntity<Collections> delete(@PathVariable Integer collectionId) {
            collectionsService.delete(collectionId);
            return ResponseEntity.ok().build();
    }


}
