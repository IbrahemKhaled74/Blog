package com.springboot.blog.exception;

import com.springboot.blog.model.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorDetails>notFoundExceptionHandler(ResourceNotFoundException resourceNotFoundException,
                                                         WebRequest webRequest){
        ErrorDetails error = ErrorDetails.builder()
                .message(resourceNotFoundException.getMessage())
                .timestamp(new Date())
                .details(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BlogException.class)
    ResponseEntity<ErrorDetails>blogExceptionHandler(BlogException blogException,
                                                         WebRequest webRequest){
        ErrorDetails error = ErrorDetails.builder()
                .message(blogException.getMessage())
                .timestamp(new Date())
                .details(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorDetails>exceptionHandler(Exception exception,
                                                         WebRequest webRequest){
        ErrorDetails error = ErrorDetails.builder()
                .message(exception.getMessage())
                .timestamp(new Date())
                .details(webRequest.getDescription(false))
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
