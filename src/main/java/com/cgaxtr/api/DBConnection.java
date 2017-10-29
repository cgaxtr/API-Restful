package com.cgaxtr.api;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private Connection con;
    private static DBConnection INSTANCE = null;

    private DBConnection() {
        String host = "127.0.0.1";
        String user = "root";
        String pass = "";
        String db = "java";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String newConnectionURL = "jdbc:mysql://" + host + "/" + db + "?"
                    + "user=" + user + "&password=" + pass;
            this.con = DriverManager.getConnection(newConnectionURL);
        } catch (Exception e) {
            System.out.println("Error al abrir la conexión.");
            System.out.println(e.toString());
        }
    }

    public static DBConnection getInstance() {
        if (INSTANCE == null) INSTANCE = new DBConnection();
        return INSTANCE;
    }

    public Connection getConnection(){
        return this.con;
    }
}