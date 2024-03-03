package com.example.bankoperationservice.exceptions;

public class InsufficientFundsException extends IllegalArgumentException{
    public InsufficientFundsException(String message) {
        super(message);
    }
}
