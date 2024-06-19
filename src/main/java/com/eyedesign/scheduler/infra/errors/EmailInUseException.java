package com.eyedesign.scheduler.infra.errors;

public class EmailInUseException extends RuntimeException{
    public EmailInUseException(){
        super("O email fornecido já está sendo utilizado");
    }
}
