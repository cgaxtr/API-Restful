package com.cgaxtr.api.DAOs;

import com.cgaxtr.api.Credential;
import com.cgaxtr.api.DBConnection;
import com.cgaxtr.api.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private Connection con;

    public UserDAO(){
        this.con = DBConnection.getInstance().getConnection();
    }

    public boolean register(User user){

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


    public User login(Credential credential){

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

    public List<User> allUsers(){
        List<User> l = new ArrayList<User>();
        String query = "SELECT * FROM players";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                l.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;
    }

    public User getUser(int id) {
        User u = null;
        String query = "SELECT * FROM players WHERE id = ?";

        try {

            PreparedStatement stm = con.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            rs.last();

            if (rs.getRow() == 1) {
                u = new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("name"));
                u.setSurname(rs.getString("surname"));
                u.setEmail(rs.getString("email"));
                return u;
            }

        } catch (SQLException e) {

        }
        return u;
    }
}
