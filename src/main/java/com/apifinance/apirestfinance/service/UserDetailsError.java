package com.apifinance.apirestfinance.service;

public class UserDetailsError extends RuntimeException {
    public UserDetailsError(String message) {
        super(message);
    }
}
