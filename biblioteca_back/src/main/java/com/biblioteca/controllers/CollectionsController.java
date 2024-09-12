package com.biblioteca.controllers;

import com.biblioteca.models.Book;
import com.biblioteca.models.Collections;
import com.biblioteca.services.interfaces.ICollectionsService;
import lombok.AllArgsConstructor;
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

}
