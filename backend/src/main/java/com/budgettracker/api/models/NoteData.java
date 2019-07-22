package com.budgettracker.api.models;

public class NoteData {
    private String text;
    private int expenseId;

    public NoteData() {}

    public NoteData(String text, int expenseId) {
        this.setText(text);
        this.setExpenseId(expenseId);
    }

    public String getText() {
        return this.text;
    }

    public void setText(String description) {
        this.text = description;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }
}
