package com.budgettracker.api.models;

import java.math.BigDecimal;

public class IncomeReturnData {

    private int id;
    private String description;
    private int category;
    private BigDecimal amount;
    private String userEmail;

    public IncomeReturnData() {}

    public IncomeReturnData(int id, String description, int category, BigDecimal amount, String userEmail) {
        this.setId(id);
        this.setDescription(description);
        this.setCategory(category);
        this.setAmount(amount);
        this.setUserEmail(userEmail);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
