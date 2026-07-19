package com.apifinance.apirestfinance.control.exceptions;

public class NotEnoughBalanceError extends RuntimeException {
    public NotEnoughBalanceError(String message) {
        super(message);
    }
}
