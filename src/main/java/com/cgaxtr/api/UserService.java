package com.cgaxtr.api;

import com.cgaxtr.api.DAOs.UserDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserService {

    private UserDAO userDAO = new UserDAO();


    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public Response register(User user){

        userDAO.register(user);

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(Credential credential){
        User u;
         u = userDAO.login(credential);

        return Response.ok(u).build();
    }

    @GET
    @Path("/user")
    @Produces("application/json")
    //@JWTTokenNeeded
    public Response users(){
        List<User> list = new ArrayList<User>();

        list = this.userDAO.allUsers();

        return Response.ok().build();
    }

    @GET
    @Path("/user/{id}")
    @Produces("application/json")
    //@JWTTokenNeeded
    public Response user(@PathParam("id") int id){

        return Response.ok().build();
    }
}
