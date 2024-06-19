package com.eyedesign.scheduler.infra.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handler404 () {
        return new ResponseEntity<String>("Email ou senha inválidos", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EmailInUseException.class)
    public ResponseEntity<?> handler409 () {
        return new ResponseEntity<String>("O email enviado já está em uso", HttpStatus.CONFLICT);
    }
}