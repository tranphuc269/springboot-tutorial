package com.bookstore.authservice.exception;


import com.bookstore.authservice.common.exception.ErrorResponse;
import com.bookstore.authservice.common.exception.GlobalExceptionHandler;
import com.bookstore.authservice.common.exception.RunTimeExceptionPlaceHolder;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AccountServiceExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(RunTimeExceptionPlaceHolder.class)
    public ResponseEntity<ErrorResponse> handleCustomException(RunTimeExceptionPlaceHolder ex) {

        ErrorResponse errorResponse = populateErrorResponse("400", ex.getMessage());
        log.error("Something went wrong, Exception : " + ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(InvalidFormatException ex) {

        ErrorResponse errorResponse = populateErrorResponse("400", ex.getMessage());
        log.error("Something went wrong, Exception : " + ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCustomException(Exception ex) {

        ErrorResponse errorResponse = populateErrorResponse("500",
                ex.getMessage());
        log.error("Something went wrong, Exception : " + ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(AccessDeniedException ex) {

        ErrorResponse errorResponse = populateErrorResponse("401",
                ex.getMessage());
        log.error("Something went wrong, Exception : " + ex.getMessage());
        ex.printStackTrace();
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

    }
}