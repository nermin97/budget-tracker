package com.budgettracker.hibernate.services;

import com.budgettracker.hibernate.dao.IncomeDao;
import com.budgettracker.hibernate.entity.Income;

import java.util.List;

public class IncomeService {

    private static IncomeDao incomeDao;

    public IncomeService() { incomeDao = new IncomeDao(); }

    public Income save(Income income) {
        int id = incomeDao.save(income);
        return incomeDao.get(id);
    }

    public Income get(int id) { return incomeDao.get(id); }

    public void update(Income income) {
        incomeDao.update(income);
    }

    public List<Income> getAll(int userId) {
        return incomeDao.getAll(userId);
    }

    public void delete(Income income) { incomeDao.delete(income); }
}
