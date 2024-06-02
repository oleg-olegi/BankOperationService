package com.example.bankoperationservice.exceptions;

public class UserIsAlreadyExistsException extends IllegalArgumentException {
    public UserIsAlreadyExistsException(String message) {
        super(message);
    }
}
