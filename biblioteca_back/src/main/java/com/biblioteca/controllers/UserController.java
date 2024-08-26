package com.biblioteca.controllers;

import com.biblioteca.models.User;
import com.biblioteca.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin()
@AllArgsConstructor
@RequestMapping(value = "api/users")
public class UserController {

    private IUserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> update(@PathVariable Integer userId, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(userId, user));
    }


}
