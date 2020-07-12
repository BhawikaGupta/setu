package com.billing.setu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidRefIdException extends RuntimeException {
    public InvalidRefIdException(String message) {
        super(message);
    }
}