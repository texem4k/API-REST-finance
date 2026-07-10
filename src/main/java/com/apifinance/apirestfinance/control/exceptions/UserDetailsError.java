package com.apifinance.apirestfinance.control.exceptions;

public class UserDetailsError extends RuntimeException {
    public UserDetailsError(String message) {
        super(message);
    }
}
