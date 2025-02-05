package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.models.User;
import com.biblioteca.requests.UserRegisterRequest;
import com.biblioteca.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin()
@AllArgsConstructor
@RequestMapping(value = "api/users")
public class UserController {

    private IUserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(userService.getUserByUserId(userId));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable Integer userId, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.update(userId, user));
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
        }catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequest request) {
        try {
            userService.register(request);
            return ResponseEntity.ok("User registered successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

}
