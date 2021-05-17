package com.example.diplomaproject.data.database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {

    String username, pass, host, port, database;
    @SuppressLint("NewApi")
    public Connection connection(){
        host = "gstuenrollee.database.windows.net";
        database = "EnrolleeDB";
        username = "enrolleeadmin";
        pass = "12345678Mm";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ host + ":"+ port+";"+ "databasename="+ database+
                    ";user="+username+";password="+pass+";";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }
        return connection;
    }
}
