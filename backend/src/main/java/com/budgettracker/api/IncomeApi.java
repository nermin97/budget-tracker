package com.budgettracker.api;

import com.budgettracker.api.models.Deleted;
import com.budgettracker.api.models.IncomeData;
import com.budgettracker.api.models.IncomeReturnData;
import com.budgettracker.hibernate.entity.Income;
import com.budgettracker.hibernate.entity.User;
import com.budgettracker.hibernate.services.IncomeService;
import com.budgettracker.hibernate.services.UserService;
import com.budgettracker.security.AuthenticatedUserDetails;
import com.budgettracker.api.util.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

@Path(Constants.INCOME_API_PATH)
public class IncomeApi {

    @Context
    private SecurityContext securityContext;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        try {
            AuthenticatedUserDetails authenticatedUserDetails = (AuthenticatedUserDetails) securityContext.getUserPrincipal();
            List<Income> list = new IncomeService().getAll(authenticatedUserDetails.getId());
            List<IncomeReturnData> dataList = new ArrayList<>();
            if(list != null && !list.isEmpty()) {
                for (Income i : list)
                    dataList.add(new IncomeReturnData(i.getId(), i.getDescription(), i.getCategory(), i.getAmount(), i.getUser().getEmail()));
            }
            return Response.ok(dataList, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(IncomeData incomeData) {
        try {
            Income income = getIncome(incomeData);
            income = new IncomeService().save(income);
            IncomeReturnData returnData = new IncomeReturnData(income.getId(), income.getDescription(), income.getCategory(), income.getAmount(), income.getUser().getEmail());
            return Response.ok(returnData, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @POST
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, IncomeData incomeData) {
        try {
            Income income = getIncome(incomeData);
            income.setId(id);
            new IncomeService().update(income);
            IncomeReturnData returnData = new IncomeReturnData(income.getId(), income.getDescription(), income.getCategory(), income.getAmount(), income.getUser().getEmail());
            return Response.ok(returnData, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }


    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        try {
            Income income = new IncomeService().get(id);
            Deleted deletedIncome = null;
            if(income != null) {
                try {
                    new IncomeService().delete(income);
                    deletedIncome = new Deleted(income.getId(), true);
                } catch (Exception e) { // exception for deleting from db
                    deletedIncome = new Deleted(income.getId(), false);
                }
            } else {
                return errorType("Income with id: " + id + ", does not exist!");
            }
            return Response.ok(deletedIncome, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    private Income getIncome(IncomeData incomeData) {
        AuthenticatedUserDetails authenticatedUserDetails = (AuthenticatedUserDetails) securityContext.getUserPrincipal();

        User user = new User();
        user.setId(authenticatedUserDetails.getId());

        Income income = new Income(incomeData.getDescription(), incomeData.getCategory(), incomeData.getAmount(), user);
        return income;
    }

    private Response errorType(String message) {
        return Response.serverError().entity(message).build();
    }

}
