package com.example.endterm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeadlineDB extends Database{
    public ResultSet getDeadline(Deadline deadline){
        ResultSet resultSet = null;
        String select = "SELECT * FROM deadlines WHERE title=? AND course=?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);
            preparedStatement.setString(1, deadline.title);
            preparedStatement.setString(2, deadline.course);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }
    public List<Deadline> getDeadlineList(String group){
        List<Deadline> returnDeadlines = new ArrayList<>();
        ResultSet resultSet = null;
        String select = "SELECT * FROM deadlines WHERE \"group\"=? AND time > CURRENT_TIMESTAMP ORDER BY time asc;";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(select);

            preparedStatement.setString(1, group);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Deadline deadline = new Deadline();
                deadline.title = resultSet.getString(2);
                deadline.timestampEnd = resultSet.getTimestamp(3);
                deadline.course = resultSet.getString(4);
                returnDeadlines.add(deadline);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return returnDeadlines;
    }
    public void addDeadline(Deadline deadline, User user){
        String insert = "INSERT INTO deadlines ( title,\"time\",course, \"group\") VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(insert);
            preparedStatement.setString(1, deadline.title);
            preparedStatement.setTimestamp(2, deadline.timestampEnd);
            preparedStatement.setString(3, deadline.course);
            preparedStatement.setString(4, user.group);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void deleteOldDeadlines(){
        String delete = "DELETE FROM deadlines WHERE time < CURRENT_TIMESTAMP";
        try{
            PreparedStatement preparedStatement = getConnection().prepareStatement(delete);
            preparedStatement.executeUpdate();
        }catch (SQLException | ClassNotFoundException e){
            System.out.println("deleteOldDeadlines");
        }
    }
}
