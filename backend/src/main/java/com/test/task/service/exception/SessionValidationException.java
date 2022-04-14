package com.test.task.service.exception;


public class SessionValidationException extends RuntimeException {
    public SessionValidationException(String message) {
        super(message);
    }
}
