package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("org.postgresql.Driver"); //searching for the postgres driver, which we have as a dependency
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //This tells us in the console what went wrong
            System.out.println("problem occurred locating PostgreSQL driver");
        }

        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=rev_project_zero_schema"; //schema name
        String username = "postgres";//PostgreSQL database
        String password = "password";//PostgreSQL password

        return DriverManager.getConnection(url, username, password);
    }
}
