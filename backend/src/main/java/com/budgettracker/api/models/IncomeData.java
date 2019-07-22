package com.budgettracker.api.models;

import java.math.BigDecimal;

public class IncomeData {

    private String description;
    private int category;
    private BigDecimal amount;

    public IncomeData() {}

    public IncomeData(String description, int category, BigDecimal amount) {
        this.setDescription(description);
        this.setCategory(category);
        this.setAmount(amount);
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
