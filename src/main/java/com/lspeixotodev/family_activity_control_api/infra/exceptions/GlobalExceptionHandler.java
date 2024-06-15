package com.lspeixotodev.family_activity_control_api.infra.exceptions;

import com.lspeixotodev.family_activity_control_api.dto.ErrorDetail;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorDetail> handleResourceNotFoundException(
            ResourceNotFoundException exception,
            WebRequest webRequest
    ) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDetail errorDetails = new ErrorDetail(
                Instant.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                status.value()
        );

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDetails);
    }

    @ExceptionHandler({ResourceAlreadyExistsException.class})
    public ResponseEntity<ErrorDetail> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException exception,
            WebRequest webRequest
    ) {

        HttpStatus status = HttpStatus.CONFLICT;

        ErrorDetail errorDetails = new ErrorDetail(
                Instant.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                status.value()
        );

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDetails);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest webRequest
    ) {

        //Map<String, String> errors = new HashMap<>();

        List<String> errorMessages = new ArrayList<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();

            //errors.put(fieldName, message);

            errorMessages.add(message);
        });

        String joinedErrors = String.join(" ", errorMessages);

        HttpStatus specificStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ErrorDetail errorDetails = new ErrorDetail(
                Instant.now(),
                joinedErrors,
                webRequest.getDescription(false),
                specificStatus.value()
        );

        return ResponseEntity
                .status(specificStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDetails);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorDetail> handleIllegalArgumentException(
            IllegalArgumentException exception,
            WebRequest webRequest
    ) {

        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErrorDetail errorDetails = new ErrorDetail(
                Instant.now(),
                "Some argument is invalid check please!",
                webRequest.getDescription(false),
                status.value()
        );

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDetails);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorDetail> handleGeneralException(
            Exception exception,
            WebRequest webRequest
    ) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ErrorDetail errorDetails = new ErrorDetail(
                Instant.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                status.value()
        );

        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(errorDetails);
    }
}
