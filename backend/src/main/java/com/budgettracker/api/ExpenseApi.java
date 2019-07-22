package com.budgettracker.api;


import com.budgettracker.api.models.*;
import com.budgettracker.api.util.Constants;
import com.budgettracker.hibernate.entity.Expense;
import com.budgettracker.hibernate.entity.Note;
import com.budgettracker.hibernate.entity.User;
import com.budgettracker.hibernate.services.ExpenseService;
import com.budgettracker.hibernate.services.UserService;
import com.budgettracker.security.AuthenticatedUserDetails;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path(Constants.EXPENSE_API_PATH)
public class ExpenseApi {

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            AuthenticatedUserDetails authenticatedUserDetails = (AuthenticatedUserDetails) securityContext.getUserPrincipal();
            User user = new UserService().getById(authenticatedUserDetails.getId());
            List<Expense> list = new ExpenseService().getAll(user.getId());
            List<ExpenseReturnData> dataList = new ArrayList<>();
            if(list != null && !list.isEmpty()) {
                for (Expense e : list)
                    dataList.add(
                            new ExpenseReturnData(e.getId(),
                                    e.getDescription(), e.getCategory(),
                                    e.getAmount(), getNotesData(e.getId()),
                                    false,  e.getUser().getEmail()));
            }
            return Response.ok(dataList, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ExpenseData expenseData) {
        try {
            Expense expense = getExpense(expenseData);
            expense = new ExpenseService().save(expense);
            return returnExpenseReturnData(expense);
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, ExpenseData expenseData) {
        try {
            Expense expense = getExpense(expenseData);
            expense.setId(id);
            new ExpenseService().update(expense);
            return returnExpenseReturnData(expense);
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        try {
            Expense expense = new ExpenseService().get(id);
            Deleted deletedExpense = null;
            if(expense != null) {
                try {
                    new ExpenseService().delete(expense);
                    deletedExpense = new Deleted(expense.getId(), true);
                } catch (Exception e) { // exception for deleting from db
                    deletedExpense = new Deleted(expense.getId(), false);
                }
            } else {
                return errorType("Expense with id: " + id + ", does not exist!");
            }
            return Response.ok(deletedExpense, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @POST
    @Path("{id}/note")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNote(@PathParam("id") int expenseId, NoteData noteData) {
        try {
            Note note = new Note(noteData.getText(), new ExpenseService().get(expenseId));
            new ExpenseService().saveNote(note);
            return returnNoteReturnData(note);
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }


    private Expense getExpense(ExpenseData expenseData) {
        AuthenticatedUserDetails authenticatedUserDetails = (AuthenticatedUserDetails) securityContext.getUserPrincipal();
        User user = new UserService().getById(authenticatedUserDetails.getId());
        return new Expense(expenseData.getDescription(), expenseData.getCategory(), expenseData.getAmount(), user);
    }

    private Response errorType(String message) {
        return Response.serverError().entity(message).build();
    }

    private Response returnExpenseReturnData(Expense expense) {
        ExpenseReturnData returnData =
                new ExpenseReturnData(expense.getId(),
                        expense.getDescription(), expense.getCategory(),
                        expense.getAmount(), getNotesData(expense.getId()),
                        false, expense.getUser().getEmail());
        return Response.ok(returnData, MediaType.APPLICATION_JSON).build();
    }

    private Response returnNoteReturnData(Note note) {
        NoteReturnData returnData = new NoteReturnData(note.getId(), note.getText(), note.getExpense().getId());
        return Response.ok(returnData, MediaType.APPLICATION_JSON).build();
    }


    private List<NoteData> getNotesData(int expenseId) {
        List<Note> notes = new ExpenseService().getNotes(expenseId);
        List<NoteData> notesData = new ArrayList<>();
        for(Note note: notes)
            notesData.add(new NoteData(note.getText(), note.getExpense().getId()));
        return notesData;
    }

}
