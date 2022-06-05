package com.pizza.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MethodNotSupportedException extends BaseException {

    public MethodNotSupportedException(String message, String errorCode) {
        super(message, errorCode);
    }

    public MethodNotSupportedException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }
}
