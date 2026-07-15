package com.apifinance.apirestfinance.control.requests;

import com.apifinance.apirestfinance.model.Category;
import com.apifinance.apirestfinance.model.User;

import java.math.BigDecimal;

public class TransactionRequest {

    private String name;
    private String description;
    private User owner;

    private Category category;

    private BigDecimal amount;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public User getOwner() {
        return owner;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
