package com.biblioteca.controllers;

import com.biblioteca.models.CollectionBooks;
import com.biblioteca.models.UserBooks;
import com.biblioteca.services.interfaces.IUserBooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping(value="api/user_books")
public class UserBooksController {

    private IUserBooksService userBooksService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserBooks>> getUserBooks(@PathVariable Integer userId, @RequestParam String status){
        return ResponseEntity.ok(userBooksService.getBooksByStatus(userId, status));
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Integer> getCountUserBooks(@PathVariable Integer userId, @RequestParam String status){
        return ResponseEntity.ok(userBooksService.getCountByStatus(userId, status));
    }

    @PutMapping("/update/{bookId}")
    public ResponseEntity<Integer> updateProgress(@PathVariable Integer bookId, @RequestParam Integer userId, @RequestParam Integer pageNum ){
        return ResponseEntity.ok(userBooksService.updateProgress(userId, bookId, pageNum));
    }

    @GetMapping("/search/{userId}")
    public ResponseEntity<List<UserBooks>> search(@PathVariable Integer userId, @RequestParam Integer type, @RequestParam String pattern){
        return ResponseEntity.ok(userBooksService.search(userId,type,pattern));
    }

    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<UserBooks> removeBook(@PathVariable Integer bookId,@RequestBody Integer userId, @RequestBody String type ) {
        userBooksService.removeBook(bookId,userId, type);
        return ResponseEntity.ok().build();
    }
}
