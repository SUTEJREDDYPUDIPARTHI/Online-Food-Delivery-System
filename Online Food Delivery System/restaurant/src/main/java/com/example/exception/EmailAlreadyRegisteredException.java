package com.example.exception;

public class EmailAlreadyRegisteredException extends Exception{
    public EmailAlreadyRegisteredException(String message){
        super(message);
    }
}