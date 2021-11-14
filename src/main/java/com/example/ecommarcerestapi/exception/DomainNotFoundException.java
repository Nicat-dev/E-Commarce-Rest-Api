package com.example.ecommarcerestapi.exception;

public class DomainNotFoundException extends RuntimeException{
    public DomainNotFoundException(String message) {
        super(message);
    }
}
