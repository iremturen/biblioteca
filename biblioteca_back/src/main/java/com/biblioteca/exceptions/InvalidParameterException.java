package com.biblioteca.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidParameterException extends IllegalArgumentException{

    public InvalidParameterException() {
        super();
    }
    public InvalidParameterException(String message) {
        super(message);
    }

}
