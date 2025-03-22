package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.models.Settings;
import com.biblioteca.services.interfaces.ISettingsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping(value = "api/settings")
public class SettingsController {

    private ISettingsService settingsService;

    @GetMapping("")
    public ResponseEntity<?> getSettings(@RequestParam String infoType) {
        try {
            List<Settings> settings = settingsService.getSettingsByType(infoType.toUpperCase());
            return ResponseEntity.ok(settings);
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }

}
