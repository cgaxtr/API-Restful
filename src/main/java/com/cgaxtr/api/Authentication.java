package com.cgaxtr.api;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class Authentication {
    /*
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response authenticationUser(Credential credential){

        String email = credential.getEmail();
        String password = credential.getPassword();

        //authenticate user
        Connection con = DBConnection.getInstance().getConnection();

        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM test WHERE email=? AND password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if(!rs.isBeforeFirst()){
                System.out.println("sin coincidencias");
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        String token = issueToken(email);

        return Response.ok().entity(token).build();
    }

    */
    public static String issueToken(String email){

        String token = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256("test");
            token = JWT.create()
                    .withIssuer("Federacion Española de Aquavolley")
                    .withSubject(email)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }
}
