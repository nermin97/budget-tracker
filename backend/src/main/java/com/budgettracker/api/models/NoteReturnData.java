package com.budgettracker.api.models;

public class NoteReturnData {

    private int id;
    private String text;
    private int expenseId;

    public NoteReturnData() {}

    public NoteReturnData(int id, String text, int expenseId) {
        this.setId(id);
        this.setText(text);
        this.setExpenseId(expenseId);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
