package com.example.diplomaproject.ui.authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.diplomaproject.CreateAccountActivity;
import com.example.diplomaproject.MainActivity;
import com.example.diplomaproject.R;
import com.example.diplomaproject.ViewAccountActivity;
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
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "SELECT UserID FROM ENROLLEEUSER WHERE Login='" + email +
                        "' AND Password='" + pass + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null){
                    Intent intent = new Intent(getBaseContext(), ViewAccountActivity.class);
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
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "INSERT INTO ENROLLEEUSER VALUES ('"+
                        email + "', '" + pass + "')";
                Statement statement = connection.createStatement();
                int resultSet = statement.executeUpdate(query);
                if(resultSet != 0){
                    Intent intent = new Intent(getBaseContext(), CreateAccountActivity.class);
                    startActivity(intent);
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            ConnectionResult = exception.getMessage();
        }
    }

    public void onBackBtnClick(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        closeActivity();
    }

    private void closeActivity() {
        this.finish();
    }
}