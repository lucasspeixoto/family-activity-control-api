package com.lspeixotodev.family_activity_control_api.infra.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;


@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ResourceAlreadyExistsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final String message;

    public ResourceAlreadyExistsException(String message) {
        super(String.format(message));
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
