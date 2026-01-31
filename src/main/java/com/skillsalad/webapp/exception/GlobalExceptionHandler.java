package com.skillsalad.webapp.exception;

import com.skillsalad.webapp.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailExists(
        EmailAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(HttpStatus.CONFLICT.value(),
                        ex.getMessage()
                ));
    }
    public ResponseEntity<ErrorResponse>handleGeneric(Exception ex){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal server error"

                ));
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(
            InvalidCredentialsException IC){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED) //The 401 error(login failure)
                .body(new ErrorResponse(
                        HttpStatus.UNAUTHORIZED.value(),
                        IC.getMessage()
                ));
    }
}
