package com.apifinance.apirestfinance.control.requests;

import java.math.BigDecimal;

public class TransactionRequest {

    private String name;
    private String description;
    private String ownerEmail;

    private BigDecimal amount;

    private String type;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}
