package com.lspeixotodev.family_activity_control_api.infra.exceptions;

import org.springframework.http.HttpStatus;

public class PlatformException extends RuntimeException {

    private final HttpStatus status;

    private final String message;

    public PlatformException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public PlatformException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

