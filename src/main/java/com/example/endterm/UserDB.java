package com.example.endterm;

import java.sql.*;

public class UserDB extends Database{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(connectionUrl, USER_DB, PASSWORD_DB);
        return connection;
    }
    public void signUpUser(String login, String password, String name, String group) {
        String insert = "INSERT INTO users (login,password,name,\"group\") VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, group);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public User getUser(User user){
        User result = new User();
        ResultSet resultSet = null;
        String select = "SELECT * FROM users WHERE login=? AND password=?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.setId(resultSet.getInt(1));
                result.setName(resultSet.getString(4));
                result.setLogin(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                result.setGroup(resultSet.getString(5));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public ResultSet getResultSetUser(User user) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM users WHERE login=? AND password=?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
    public void updateUser(User user){
        String update = "UPDATE users SET login=?, password=?, name=?, \"group\"=? WHERE id=?;";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(update);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.group);
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeQuery();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error");
        }
    }
}
