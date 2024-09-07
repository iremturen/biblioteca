package com.biblioteca.controllers;

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

    @GetMapping("/now_reading/{userId}")
    public ResponseEntity<List<UserBooks>> getNowReading(@PathVariable int userId){
        return ResponseEntity.ok(userBooksService.getNowReading(userId));
    }

    @GetMapping("/now_reading/count/{userId}")
    public ResponseEntity<Integer> getCountNowReading(@PathVariable int userId){
        return ResponseEntity.ok(userBooksService.getCountNowReading(userId));
    }

    @GetMapping("/will_read/{userId}")
    public ResponseEntity<List<UserBooks>> getWillRead(@PathVariable int userId){
        return ResponseEntity.ok(userBooksService.getWillRead(userId));
    }

    @GetMapping("/will_read/count/{userId}")
    public ResponseEntity<Integer> getCountWillRead(@PathVariable int userId){
        return ResponseEntity.ok(userBooksService.getCountWillRead(userId));
    }

    @GetMapping("/finished/{userId}")
    public ResponseEntity<List<UserBooks>> getFinished(@PathVariable int userId){
        return ResponseEntity.ok(userBooksService.getFinished(userId));
    }

    @GetMapping("/finished/count/{userId}")
    public ResponseEntity<Integer> getCountFinished(@PathVariable int userId){
        return ResponseEntity.ok(userBooksService.getCountFinished(userId));
    }
}
