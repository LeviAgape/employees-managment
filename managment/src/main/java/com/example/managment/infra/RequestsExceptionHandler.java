package com.example.managment.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestsExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404(){
        return ResponseEntity.badRequest().body("O dado não foi achado");
    }
}
