package com.budgettracker.hibernate.dao;
import com.budgettracker.hibernate.entity.Income;
import java.util.List;

public class IncomeDao extends Dao<Income> {

    @SuppressWarnings("unchecked")
    public List<Income> getAll(int userId) {
        openSession();
        List<Income> list = session.createQuery("from Income  where user_id = :userId").setParameter("userId", userId).list();
        closeSession();
        return list;
    }

    public Income get(int id) {
        openSession();
        Income i = session.get(Income.class, id);
        session.close();
        return i;
    }
}
