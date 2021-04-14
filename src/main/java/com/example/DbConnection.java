package com.example;

import java.sql.*;

public class DbConnection {
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/zoo";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "root";


    public Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(DB_DRIVER);
            System.out.println("Connection successful!");
            connection = DriverManager.getConnection(DB_URL,
                    DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection to zoo successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("Connection failed...");
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("Connection to zoo failed");
            throwables.printStackTrace();
        }
        return connection;
    }
}
