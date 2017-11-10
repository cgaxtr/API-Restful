package com.cgaxtr.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection con;

    public UserDAO(){
        this.con = DBConnection.getInstance().getConnection();
    }

    boolean register(User user){

        String queryInsert = " INSERT INTO players (name, surname, email, password) VALUE (?, ?, ?, ?)";

        try {
            PreparedStatement preparedStmt = con.prepareStatement(queryInsert);
            preparedStmt.setString (1, user.getName());
            preparedStmt.setString (2, user.getSurname());
            preparedStmt.setString (3, user.getEmail());
            preparedStmt.setString (4, user.getPassword());

            preparedStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    User login(Credential credential){

        String query = "SELECT * FROM players WHERE email=? and password=?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, credential.getEmail());
            preparedStatement.setString(2, credential.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            rs.last();

            if (rs.getRow() == 1){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                return u;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }
}
