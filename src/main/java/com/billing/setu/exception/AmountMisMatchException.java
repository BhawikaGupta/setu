package com.billing.setu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmountMisMatchException extends RuntimeException {
    public AmountMisMatchException(String message) {
        super(message);
    }
}