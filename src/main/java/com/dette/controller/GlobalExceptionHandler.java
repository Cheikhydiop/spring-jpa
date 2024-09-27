package com.dette.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        // Journalisez l'exception (vous pouvez utiliser un logger ici)
        System.err.println("Une erreur s'est produite : " + e.getMessage());
        
        // Retournez un message d'erreur générique
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Une erreur inattendue s'est produite : " + e.getMessage());
    }
}
