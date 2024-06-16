package com.example.bankoperationservice.exceptions;

public class BankAccountNotFoundException extends IllegalArgumentException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
