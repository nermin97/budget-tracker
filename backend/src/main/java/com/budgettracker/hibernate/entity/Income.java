package com.budgettracker.hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Entity
@Table(name = "income", schema = "public")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Description cannot be null")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Category cannot be null")
    @Column(name = "category")
    private int category;

    @NotNull(message = "Amount cannot be null")
    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Income() {}

    public Income(String description, int category, BigDecimal amount, User user) {
        this.setDescription(description);
        this.setCategory(category);
        this.setAmount(amount);
        this.setUser(user);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
