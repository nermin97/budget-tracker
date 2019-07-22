package com.budgettracker.api;
import com.budgettracker.api.models.UserAuth;
import com.budgettracker.api.models.UserCredentials;
import com.budgettracker.hibernate.entity.User;
import com.budgettracker.hibernate.services.UserService;
import com.budgettracker.api.util.Constants;
import com.budgettracker.security.JwtHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(Constants.AUTHORIZATION_API_PATH)
public class Auth {

    @POST
    @Path(Constants.LOGIN_API_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredentials uc) {
        try{
            Response response = validateCredentials(uc);
            if(response != null) return response;

            User user = new UserService().get(uc);
            if(user == null) {
                return errorType("User does not exist!");
            }
            String token = JwtHelper.generateToken(user);
            UserAuth userAuth = new UserAuth(token, user.getEmail());
            return Response.ok(userAuth, MediaType.APPLICATION_JSON).build();
        } catch (Exception e){
            return errorType(e.getMessage());
        }
    }

    @POST
    @Path(Constants.REGISTRATION_API_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserCredentials uc){
        try{
            Response response = validateCredentials(uc);
            if(response != null) return response;
            if(new UserService().getByEmail(uc.getEmail()) != null)
                return errorType("Email is taken!");

            User user = new UserService().save(uc);
            String token = JwtHelper.generateToken(user);
            UserAuth userAuth = new UserAuth(token, user.getEmail());
            return Response.ok(userAuth, MediaType.APPLICATION_JSON).build();
        } catch (Exception e){
            return errorType(e.getMessage());
        }
    }

    private Response errorType(String message) {
        return Response.serverError().entity(message).build();
    }

    private Response validateCredentials(UserCredentials uc) {
        if(uc.getEmail().isEmpty())
            return errorType("Email field is empty!");
        if(uc.getPassword().isEmpty())
            return errorType("Password field is empty!");
        return null;
    }


}

