package com.cgaxtr.api;

import javax.ws.rs.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserService {

    private UserDAO userDAO = new UserDAO();


    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public User register(User user){

        userDAO.register(user);

        return user;
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public User login(Credential credential){
        User u;

         u = userDAO.login(credential);

        return u;
    }



    /* debug stuff
    @GET
    @Path("/user/{id}")
    @Produces("application/json")
    public User user(@PathParam("id") int id){
        User u = new User();
        u.setId(id);
        u.setName("Carlos");

        return u;
    }

    @GET
    @Path("/user")
    @Produces("application/json")
    public List<User> users(){
        List<User> list = new ArrayList<User>();
        User u = new User();
        u.setId(1);
        u.setName("Carlos");
        list.add(u);

        User u2 = new User();
        u2.setId(2);
        u2.setName("Carlos");
        list.add(u2);

        return list;
    }

    @GET
    @Path("/test")
    @Produces("application/json")
    public List<User> test(){
        List<User> list = new ArrayList<>();
        Connection con = DBConnection.getInstance().getConnection();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM test");
            while ( rs.next() ) {
                User u = new User();
                u.setName(rs.getString("nombre"));
                u.setSurname(rs.getString("apellido"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @GET
    @Path("/unsecured")
    @Produces("text/plain")
    public String unsecured(){
        return "Unsecured";
    }

    @GET
    @Path("/secured")
    @Produces("text/plain")
    @JWTTokenNeeded
    public String secured(){
        return "Secured";
    }
    */
}
