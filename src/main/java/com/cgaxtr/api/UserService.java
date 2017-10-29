package com.cgaxtr.api;

import javax.ws.rs.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Path("/user")
public class UserService {

    @GET
    @Path("/user/{id}")
    @Produces("application/json")
    public User user(@PathParam("id") int id){
        User u = new User();
        u.setId(id);
        u.setNombre("Carlos");

        return u;
    }

    @GET
    @Path("/user")
    @Produces("application/json")
    public List<User> users(){
        List<User> list = new ArrayList<User>();
        User u = new User();
        u.setId(1);
        u.setNombre("Carlos");
        list.add(u);

        User u2 = new User();
        u2.setId(2);
        u2.setNombre("Carlos");
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
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @POST
    @Path("/register")
    @Consumes("application/json")
    @Produces("application/json")
    public User register(User user){


        System.out.println("nombre: " + user.getNonmbre());
        System.out.println("apellido: " + user.getApellido());
        return user;
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
}
