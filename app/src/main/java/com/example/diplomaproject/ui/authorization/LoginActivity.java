package com.example.diplomaproject.ui.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomaproject.ui.account.CreateAccountActivity;
import com.example.diplomaproject.R;
import com.example.diplomaproject.ui.account.ViewAccountActivity;
import com.example.diplomaproject.data.database.ConnectionHelper;
import com.example.diplomaproject.data.database.DatabaseAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    String ConnectionResult="";
    Connection connection;
    EditText emailEdit;
    EditText passEdit;
    DatabaseAdapter databaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        emailEdit = findViewById(R.id.editTextEmail);
        passEdit = findViewById(R.id.editTextPassword);

    }

    public void onEnterBtnClick(View view) throws SQLException {
        String email = emailEdit.getText().toString();
        String pass = passEdit.getText().toString();
        /*
        boolean check = databaseAdapter.Authorization(email, pass);
        if(check){
            //Открыть личный кабинет в режиме просмотра
            Intent intent = new Intent(getBaseContext(), CreateAccountActivity.class);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(this, "Проблема с подключением", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
        }*/
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            int id = 0;
            if (connection != null) {
                String query = "SELECT UserID FROM ENROLLEEUSER WHERE Login=N'" + email +
                        "' AND Password=N'" + pass + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null){
                    while (resultSet.next()){
                        id = resultSet.getInt("UserID");
                    }
                    Intent intent = new Intent(getBaseContext(), ViewAccountActivity.class);
                    intent.putExtra("userId", id);
                    startActivity(intent);
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            ConnectionResult = exception.getMessage();
        }
    }

    public void onRegisterBtnClick(View view) throws SQLException {
        String email = emailEdit.getText().toString();
        String pass = passEdit.getText().toString();
        databaseAdapter = new DatabaseAdapter();
        /*boolean checkRegistration = databaseAdapter.Registration(email, pass);
        if(checkRegistration){
            //Открыть личный кабинет в режиме заполнения
        }
        else{
            Toast toast = Toast.makeText(this, "Проблема с подключением", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }*/
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            int id = 0;
            if (connection != null) {
                String query = "INSERT INTO ENROLLEEUSER VALUES ('"+
                        email + "', '" + pass + "')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(query);

                String selectQuery = "SELECT UserID FROM ENROLLEEUSER WHERE Login=N'" + email +
                        "' AND Password=N'" + pass + "'";
                ResultSet resultSet = statement.executeQuery(selectQuery);
                if(resultSet != null){
                    while (resultSet.next()){
                        id = resultSet.getInt("UserID");
                    }
                    Intent intent = new Intent(getBaseContext(), CreateAccountActivity.class);
                    intent.putExtra("userId", id);
                    startActivity(intent);
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            ConnectionResult = exception.getMessage();
        }
    }

}