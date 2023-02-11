package com.exception;

import org.springframework.http.HttpStatus;

public class CustomerException extends RuntimeException {

    public CustomerException(String message, HttpStatus notFound) {
        super(message);
    }
}
