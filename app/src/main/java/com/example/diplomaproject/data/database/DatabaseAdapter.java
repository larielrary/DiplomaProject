package com.example.diplomaproject.data.database;

import android.util.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAdapter {

    ConnectionHelper connectionHelper;
    Connection connection;
    String ConnectionResult = "";

    public DatabaseAdapter(){

    }

    public void InsertEnrollee(){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "Insert ......";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                /*while (resultSet.next()) {
                    textView1.setText(resultSet.getString(1));
                    textView2.setText(resultSet.getString(2));
                }*/
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {

        }
    }

    public void InsertPriority(){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "Insert ......";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                /*while (resultSet.next()) {
                    textView1.setText(resultSet.getString(1));
                    textView2.setText(resultSet.getString(2));
                }*/
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
    }

    public void Select(){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "Select * from FACULTY";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                /*while (resultSet.next()) {
                    textView1.setText(resultSet.getString(1));
                    textView2.setText(resultSet.getString(2));
                }*/
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
    }

    public boolean Authorization(String email, String pass) throws SQLException {

            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "Select * from ENROLLEEUSER Where Login = " + email +
                        "and Password = " + pass;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null){
                    return true;
                }
                else{
                    return false;
                }
            } else {
                return false;
            }
    }

    public boolean Registration(String email, String pass) throws SQLException {

            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "Insert into ENROLLEEUSER VALUES ('"+ email + "', '" + pass + "')";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null){
                    return true;
                }
                else{
                    return false;
                }
            } else {
                return false;
            }
    }
}
