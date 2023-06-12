package com.revature.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    // We need three things to make this a proper singleton class
    // private static instance of a connection --> holds the connection itself
    // private constructor --> prevents other users from making their own connections
    // public static getConnection method --> the ONLY way we want users to get the connection to the db

    private static final Logger logger = LoggerFactory.getLogger(ConnectionUtil.class);
    // Private static instance
    // We initialize this to be null so there's no open connection all the time
    private static Connection conn = null;


    // private constructor
    private ConnectionUtil(){
    }

    public static Connection getConnection() throws SQLException {

        // Now is time for the singleton stuff to make sure we only every have one connection
        // Look to see if there is an OPEN connection

        if (conn != null && !conn.isClosed()){
            // This means the connections is NOT null and IS OPEN
            logger.info("Using a previously created connection");
            return conn;
        }

        try {
            Class.forName("org.postgresql.Driver"); //searching for the postgres driver, which we have as a dependency
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //This tells us in the console what went wrong
            logger.warn("problem occurred locating PostgreSQL driver");
        }

        String url = System.getenv("URL"); //schema name
        String username = System.getenv("USERNAME");//PostgreSQL database
        String password = System.getenv("PASSWORD");//PostgreSQL password

        conn = DriverManager.getConnection(url, username, password);
        logger.info("Using a new connection");

        return conn;
    }
}
