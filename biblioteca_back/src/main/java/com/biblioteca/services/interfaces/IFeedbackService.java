package com.biblioteca.services.interfaces;

import org.springframework.validation.annotation.Validated;

@Validated
public interface IFeedbackService {

    void sendFeedBack(String email, String subject, String body);

}
