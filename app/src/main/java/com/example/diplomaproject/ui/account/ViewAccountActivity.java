package com.example.diplomaproject.ui.account;

import android.annotation.SuppressLint;
import android.content.Intent;
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
    int USER_ID;
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
    CheckBox isOutOfCompetitions;
    TextView profSubj1;
    TextView profSubj2;
    TextView subj3;
    TextView certificate;
    CheckBox isAgreeForPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        USER_ID = intent.getIntExtra("userId", 0);
        surname = findViewById(R.id.Surname);
        name = findViewById(R.id.Name);
        patronymic = findViewById(R.id.Patronymic);
        passport = findViewById(R.id.Passport);
        address = findViewById(R.id.Address);
        dateOfBirth = findViewById(R.id.DateOfBirth);
        phoneNumber = findViewById(R.id.PhoneNumber);
        educationForm = findViewById(R.id.EducationForm);
        isAimed = findViewById(R.id.IsAimed);
        isWithoutExams = findViewById(R.id.IsWithoutExams);
        isBudget = findViewById(R.id.IsBudget);
        isOutOfCompetitions = findViewById(R.id.IsOutOfCompetitions);
        profSubj1 = findViewById(R.id.ProfSubj1EE);
        profSubj2 = findViewById(R.id.ProfSubj2EE);
        subj3 = findViewById(R.id.Subj3EE);
        certificate = findViewById(R.id.CertificateScoreX10);
        isAgreeForPayment = findViewById(R.id.IsAgreeForPayment);
        viewInfo();
    }


    @SuppressLint("SetTextI18n")
    public void viewInfo(){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "SELECT e.Surname, e.Name, e.Patronymic, e.Passport, e.Address, " +
                        "e.DateOfBirth, e.PhoneNumber, f.Name, e.IsAimed, e.IsWithoutEntranceExams," +
                        "e.IsBudget, e.IsOutOfCompetition, e.ProfSubj1EE, e.ProfSubj2EE, e.Subj3EE, " +
                        "e.CertificateScoreX10, e.IsAgreeForPayment FROM ENROLLEE e JOIN " +
                        "EDUCATIONFORM f ON f.FormId = e.FormID WHERE e.UserID=" + USER_ID + "";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null){

                        //String surnameS = resultSet.getString("Surname");
                        surname.setText(resultSet.getString("Surname"));
                        name.setText(resultSet.getString("e.Name"));
                        patronymic.setText(resultSet.getString("Patronymic"));
                        passport.setText(resultSet.getString("Passport"));
                        address.setText(resultSet.getString("Address"));
                        dateOfBirth.setText(resultSet.getDate("DateOfBirth").toString());
                        phoneNumber.setText(resultSet.getString("PhoneNumber"));
                        educationForm.setText(resultSet.getString("f.Name"));
                        isAimed.setChecked(convertIntToBoolean(resultSet.getInt("IsAimed")));
                        isWithoutExams.setChecked(convertIntToBoolean(resultSet.getInt("IsWithoutEntranceExams")));
                        isBudget.setChecked(convertIntToBoolean(resultSet.getInt("IsBudget")));
                        isOutOfCompetitions.setChecked(convertIntToBoolean(resultSet.getInt("IsOutOfCompetition")));
                        profSubj1.setText(resultSet.getInt("ProfSubj1EE"));
                        profSubj2.setText(resultSet.getInt("ProfSubj2EE"));
                        subj3.setText(resultSet.getInt("Subj3EE"));
                        certificate.setText(Double.toString(resultSet.getDouble("CertificateScoreX10")));
                        isAgreeForPayment.setChecked(convertIntToBoolean(resultSet.getInt("IsAgreeForPayment")));
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            ConnectionResult = exception.getMessage();
        }
    }

    public boolean convertIntToBoolean(int intValue){
        return intValue > 0;
    }
}