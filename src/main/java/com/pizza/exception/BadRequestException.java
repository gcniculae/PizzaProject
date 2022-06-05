package com.pizza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException(String message, String errorCode) {
        super(message, errorCode);
    }

    public BadRequestException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
