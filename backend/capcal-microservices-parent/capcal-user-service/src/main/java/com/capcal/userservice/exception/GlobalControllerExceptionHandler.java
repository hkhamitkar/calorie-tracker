package com.capcal.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
class GlobalControllerExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ResponseStatusException.class})
    public ResponseEntity<?> handleResponseStatusExceptions(
            ResponseStatusException ex) {
        return new ResponseEntity(new ErrorInfo(ex.getStatusCode().toString(),ex),ex.getStatusCode());
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<?> handleUsernameNotFoundException(
            AuthenticationException ex) {
        return new ResponseEntity(new ErrorInfo(HttpStatus.BAD_REQUEST.toString(),ex),HttpStatus.BAD_REQUEST);
    }
}
