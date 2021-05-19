package com.example.diplomaproject.ui.authorization;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.diplomaproject.R;
import com.example.diplomaproject.data.database.ConnectionHelper;
import com.example.diplomaproject.data.database.DatabaseAdapter;
import com.example.diplomaproject.ui.account.CreateAccountActivity;
import com.example.diplomaproject.ui.account.ViewAccountActivity;
import com.example.diplomaproject.validators.interfaces.ErrorCallBack;
import com.example.diplomaproject.validators.validators.Email;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements ErrorCallBack {

    HashMap<String, Boolean> emailValidation;
    HashMap<String, Object> passwordValidation;
    String ConnectionResult = "";
    Connection connection;
    ConstraintLayout CLLogin;
    EditText emailEdit;
    EditText passEdit;
    DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CLLogin = findViewById(R.id.LoginLayout);
        emailEdit = findViewById(R.id.editTextEmail);
        passEdit = findViewById(R.id.editTextPassword);

    }

    public void onEnterBtnClick(View view) throws SQLException {
        String email = emailEdit.getText().toString();
        String pass = passEdit.getText().toString();

        emailValidation = new Email.EmailBuilder(emailEdit.getText().toString())
                .setRequired(true)
                .build();

        Email emailField = new Email(LoginActivity.this);


        if (emailField.isValid(emailValidation)) {
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
            int checkExist = checkExisting(email);
            if (checkExist != 0) {
                try {
                    ConnectionHelper connectionHelper = new ConnectionHelper();
                    connection = connectionHelper.connection();
                    int id = 0;
                    if (connection != null) {
                        String query = "SELECT UserID FROM ENROLLEEUSER WHERE Login=N'" + email +
                                "' AND Password=N'" + pass + "'";
                        Statement statement = connection.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                        if (resultSet != null) {
                            while (resultSet.next()) {
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
            } else {
                showSnackBar("Пользователь не найден");
            }
        }
    }

    public void onRegisterBtnClick(View view) throws SQLException {
        String email = emailEdit.getText().toString();
        String pass = passEdit.getText().toString();
        databaseAdapter = new DatabaseAdapter();
        emailValidation = new Email.EmailBuilder(emailEdit.getText().toString())
                .setRequired(true)
                .build();

        Email emailField = new Email(LoginActivity.this);


        if (emailField.isValid(emailValidation)) {
            if (pass.length() > 5 && pass.length() < 10) {
                int checkExist = checkExisting(email);
                if (checkExist == 0) {

                    try {
                        ConnectionHelper connectionHelper = new ConnectionHelper();
                        connection = connectionHelper.connection();
                        int id = 0;
                        if (connection != null) {
                            String query = "INSERT INTO ENROLLEEUSER VALUES ('" +
                                    email + "', N'" + pass + "')";
                            Statement statement = connection.createStatement();
                            statement.executeUpdate(query);

                            String selectQuery = "SELECT UserID FROM ENROLLEEUSER WHERE Login=N'" + email +
                                    "' AND Password=N'" + pass + "'";
                            ResultSet resultSet = statement.executeQuery(selectQuery);
                            if (resultSet != null) {
                                while (resultSet.next()) {
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
                } else {
                    showSnackBar("Пользователь с таким логином уже существует");
                }

            } else {
                showSnackBar("Длина пароля должна быть в диапазоне от 6 до 9 символов");
            }
        }
    }

    private void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar
                .make(CLLogin, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
    }

    @Override
    public void onValidationError(String s) {
        hideKeyboard();
        showSnackBar(s);
    }


    public int checkExisting(String login) {
        int res = 0;
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {

                String selectQuery = "SELECT UserID FROM ENROLLEEUSER WHERE Login=N'" + login + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(selectQuery);
                if (resultSet != null) {
                    while (resultSet.next()) {
                        res++;
                    }
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            ConnectionResult = exception.getMessage();
        }
        return res;
    }
}