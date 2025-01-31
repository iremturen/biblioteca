package com.biblioteca.controllers;

import com.biblioteca.exceptions.BadRequestException;
import com.biblioteca.exceptions.InvalidParameterException;
import com.biblioteca.requests.FeedbackRequest;
import com.biblioteca.services.FeedbackService;
import com.biblioteca.services.interfaces.IFeedbackService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@CrossOrigin()
@RequestMapping(value = "api/feedback")
public class FeedbackController {

    private IFeedbackService feedbackService;

    @PostMapping("/send")
    public ResponseEntity<?> sendFeedback(@RequestBody FeedbackRequest feedbackRequest) {
        try {
            feedbackService.sendFeedBack(feedbackRequest.getEmail(), feedbackRequest.getSubject(), feedbackRequest.getBody());
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("message", "Feedback sent successfully");
            return ResponseEntity.ok(successResponse);
        } catch (BadRequestException | InvalidParameterException e) {
            Map<String, String> err = new HashMap<>();
            err.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
        }
    }
}
