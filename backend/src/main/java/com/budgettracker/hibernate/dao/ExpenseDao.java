package com.budgettracker.hibernate.dao;

import com.budgettracker.hibernate.entity.Expense;

import java.util.List;

public class ExpenseDao extends Dao<Expense> {

    public Expense get(int id) {
        openSession();
        Expense e = session.get(Expense.class, id);
        session.close();
        return e;
    }

    @SuppressWarnings("unchecked")
    public List<Expense> getAll(int userId) {
        openSession();
        List<Expense> list = session.createQuery("from Expense  where user_id = :userId").setParameter("userId", userId).list();
        closeSession();
        return list;
    }

}
