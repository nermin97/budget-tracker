package com.budgettracker.hibernate.dao;
import com.budgettracker.hibernate.entity.Note;

import java.util.List;

public class NoteDao extends Dao<Note> {

    public Note get(int id) {
        openSession();
        Note n = session.get(Note.class, id);
        session.close();
        return n;
    }

    public List<Note> getAll(int expenseId) {
        openSession();
        List<Note> list = session.createQuery("from Note where expense_id = :expenseId").setParameter("expenseId", expenseId).list();
        closeSession();
        return list;
    }

}
