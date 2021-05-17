package com.example.diplomaproject.ui.account;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomaproject.R;
import com.example.diplomaproject.data.database.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewAccountActivity extends AppCompatActivity {

    String ConnectionResult="";
    Connection connection;
    TextView surname;
    TextView name;
    TextView patronymic;
    TextView passport;
    TextView address;
    TextView dateOfBirth;
    TextView phoneNumber;
    TextView educationForm;
    CheckBox isAimed;
    CheckBox isWithoutExams;
    CheckBox isBudget;
    CheckBox inOutOfCompetitions;
    TextView profSubj1;
    TextView profSubj2;
    TextView subj3;
    TextView certificate;
    CheckBox isAgreeForPayment;
    int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void viewInfo(){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "SELECT e.Surname, e.Name, e.Patronymic, e.Passport, e.Address, " +
                        "e.DateOfBirth, e.phoneNumber, f.Name, e.IsAimed, e.IsWithoutEntranceExams," +
                        "e.IsBudget, e.IsOutOfCompetitions, e.ProfSubj1EE, e.ProfSubj2EE, e.Subj3EE, " +
                        "e.CertificateScore, e.IsAgreeForPayment FROM ENROLLEE e JOIN " +
                        "EDUCATIONFORM f ON f.FormId = e.FormID WHERE e.UserID=" + userID + "";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null){

                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            ConnectionResult = exception.getMessage();
        }
    }
}