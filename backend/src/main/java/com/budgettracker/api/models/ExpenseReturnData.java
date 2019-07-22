package com.budgettracker.api.models;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseReturnData {

    private int id;
    private String description;
    private int category;
    private BigDecimal amount;
    private List<NoteData> notes;
    private boolean showNote = false;
    private String userEmail;

    public ExpenseReturnData() {}

    public ExpenseReturnData(int id, String description, int category, BigDecimal amount, List<NoteData> note, boolean showNote, String userEmail) {
        this.setId(id);
        this.setDescription(description);
        this.setCategory(category);
        this.setAmount(amount);
        this.setNotes(note);
        this.setShowNote(showNote);
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


    public boolean isShowNote() {
        return showNote;
    }

    public void setShowNote(boolean showNote) {
        this.showNote = showNote;
    }

    public List<NoteData> getNotes() {
        return notes;
    }

    public void setNotes(List<NoteData> notes) {
        this.notes = notes;
    }
}
