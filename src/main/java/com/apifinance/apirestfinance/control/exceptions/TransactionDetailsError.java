package com.apifinance.apirestfinance.control.exceptions;

public class TransactionDetailsError extends RuntimeException {
    public TransactionDetailsError(String message) {
        super(message);
    }
}
