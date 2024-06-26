package com.eyedesign.scheduler.infra.errors;

public class InvalidDataException extends  RuntimeException{
    public InvalidDataException(String message) {
        super(message);
    }
}
