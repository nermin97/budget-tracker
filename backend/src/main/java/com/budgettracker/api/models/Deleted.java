package com.budgettracker.api.models;

public class Deleted {

    private int id;
    private boolean isDeleted;

    public Deleted() {}

    public Deleted(int id, boolean isDeleted) {
        this.setId(id);
        this.setDeleted(isDeleted);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
