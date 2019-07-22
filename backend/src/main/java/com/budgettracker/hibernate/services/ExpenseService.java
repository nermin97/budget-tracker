package com.budgettracker.hibernate.services;

import com.budgettracker.hibernate.dao.ExpenseDao;
import com.budgettracker.hibernate.dao.NoteDao;
import com.budgettracker.hibernate.entity.Expense;
import com.budgettracker.hibernate.entity.Note;
import com.budgettracker.hibernate.entity.User;

import java.util.List;

public class ExpenseService {

    private static ExpenseDao expenseDao;
    private static NoteDao noteDao;

    public ExpenseService() {
        expenseDao = new ExpenseDao();
        noteDao = new NoteDao();
    }

    public Expense get(int id) {
        return expenseDao.get(id);
    }

    public Expense save(Expense e) {
        int id = expenseDao.save(e);
        return expenseDao.get(id);
    }

    public void delete(Expense e) {
        List<Note> notes = getNotes(e.getId());
        // first we need to delete all the notes
        for(Note note: notes)
            deleteNote(note);
        expenseDao.delete(e);

    }

    public void update(Expense e) {
        expenseDao.update(e);
    }

    public List<Expense> getAll(int userId) {
        return expenseDao.getAll(userId);
    }

    // for note
    public Note saveNote(Note n) {
        int id = noteDao.save(n);
        return noteDao.get(id);
    }

    public void deleteNote(Note n) {
        noteDao.delete(n);
    }

    public List<Note> getNotes(int expenseId) {
        return noteDao.getAll(expenseId);
    }
}
