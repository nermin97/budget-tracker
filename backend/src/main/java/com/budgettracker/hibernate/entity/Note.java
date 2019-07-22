package com.budgettracker.hibernate.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "note", schema = "public")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Text cannot be null")
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    public Note() {}

    public Note(String text, Expense expense) {
        this.setText(text);
        this.setExpense(expense);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }
}
