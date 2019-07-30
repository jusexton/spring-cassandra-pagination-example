package com.sexton.example.cassandrapaginationexample.exceptions;

import com.sexton.example.cassandrapaginationexample.exceptions.responses.UserNotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserNotFoundResponse> handleUserNotFoundException(final UserNotFoundException exception) {
        final UserNotFoundResponse response = new UserNotFoundResponse(exception.getInvalidId());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
