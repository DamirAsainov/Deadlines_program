package com.example.endterm;
import java.sql.*;

public abstract class Database {
    protected final String connectionUrl = "jdbc:postgresql://localhost:5432/AITU_database";
    protected final String USER_DB = "postgres";
    protected final String PASSWORD_DB = "1234";
    protected Connection connection = null;

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(connectionUrl, USER_DB, PASSWORD_DB);
        return connection;
    }
}
