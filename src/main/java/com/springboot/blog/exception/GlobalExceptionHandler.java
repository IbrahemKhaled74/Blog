package com.springboot.blog.exception;

import com.springboot.blog.model.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String>errors =new HashMap<>();
        ex.getAllErrors().forEach((error -> {
            String fieldName = ((FieldError) error).getField();
            String message=error.getDefaultMessage();
            errors.put(fieldName,message);
        }));
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);

    }

}
