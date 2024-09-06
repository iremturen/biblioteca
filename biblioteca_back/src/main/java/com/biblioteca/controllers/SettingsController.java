package com.biblioteca.controllers;

import com.biblioteca.models.Book;
import com.biblioteca.models.Settings;
import com.biblioteca.services.interfaces.ISettingsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping(value="api/settings")
public class SettingsController {

    private ISettingsService settingsService;

    @GetMapping("/faq")
    public ResponseEntity<List<Settings>> getFAQ() {
        List<Settings> faq =settingsService.getFAQ();
        return ResponseEntity.ok(faq);
    }

    @GetMapping("/support")
    public ResponseEntity<List<Settings>> getSupport() {
        List<Settings> support =settingsService.getSupport();
        return ResponseEntity.ok(support);    }

}
