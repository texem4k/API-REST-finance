package com.apifinance.apirestfinance.control.exceptions;

public class UserPasswordError extends RuntimeException {
    public UserPasswordError(String message) {
        super(message);
    }
}
