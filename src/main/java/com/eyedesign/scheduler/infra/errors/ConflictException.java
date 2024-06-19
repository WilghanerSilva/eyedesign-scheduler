package com.eyedesign.scheduler.infra.errors;

public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
