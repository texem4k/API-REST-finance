package com.apifinance.apirestfinance.service;

public class UserPasswordError extends RuntimeException {
    public UserPasswordError(String message) {
        super(message);
    }
}
