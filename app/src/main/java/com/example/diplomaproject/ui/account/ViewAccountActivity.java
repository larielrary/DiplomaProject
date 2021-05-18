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

    String ConnectionResult = "";
    Connection connection;
    int USER_ID;
    TextView surnameT;
    TextView nameT;
    TextView patronymicT;
    TextView passportT;
    TextView addressT;
    TextView dateOfBirthT;
    TextView phoneNumberT;
    TextView educationFormT;
    CheckBox isAimedC;
    CheckBox isWithoutExamsC;
    CheckBox isBudgetC;
    CheckBox isOutOfCompetitionsC;
    TextView profSubj1T;
    TextView profSubj2T;
    TextView subj3T;
    TextView certificateT;
    CheckBox isAgreeForPaymentC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        USER_ID = intent.getIntExtra("userId", 0);
        surnameT = findViewById(R.id.Surname);
        nameT = findViewById(R.id.Name);
        patronymicT = findViewById(R.id.Patronymic);
        passportT = findViewById(R.id.Passport);
        addressT = findViewById(R.id.Address);
        dateOfBirthT = findViewById(R.id.DateOfBirth);
        phoneNumberT = findViewById(R.id.PhoneNumber);
        educationFormT = findViewById(R.id.EducationForm);
        isAimedC = findViewById(R.id.IsAimed);
        isWithoutExamsC = findViewById(R.id.IsWithoutExams);
        isBudgetC = findViewById(R.id.IsBudget);
        isOutOfCompetitionsC = findViewById(R.id.IsOutOfCompetitions);
        profSubj1T = findViewById(R.id.ProfSubj1EE);
        profSubj2T = findViewById(R.id.ProfSubj2EE);
        subj3T = findViewById(R.id.Subj3EE);
        certificateT = findViewById(R.id.CertificateScoreX10);
        isAgreeForPaymentC = findViewById(R.id.IsAgreeForPayment);
        viewInfo();
    }


    @SuppressLint("SetTextI18n")
    public void viewInfo() {
        String surname, name, patronymic, passport, address, dateOfBirth, phoneNumber, educationForm;
        boolean isAimed, isWithoutExams, isBudget, isOutOfCompetitions, isAgreeForPayment;
        int profSubj1, profSubj2, subj3;
        double certificate;
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
                if (resultSet != null) {
                    while (resultSet.next()) {
                        surname = resultSet.getString(1);
                        name = resultSet.getString(2);
                        patronymic = resultSet.getString(3);
                        passport = resultSet.getString(4);
                        address = resultSet.getString(5);
                        dateOfBirth = resultSet.getDate(6).toString();
                        phoneNumber = resultSet.getString(7);
                        educationForm = resultSet.getString(8);
                        isAimed = convertIntToBoolean(resultSet.getInt(9));
                        isWithoutExams = convertIntToBoolean(resultSet.getInt(10));
                        isBudget = convertIntToBoolean(resultSet.getInt(11));
                        isOutOfCompetitions = convertIntToBoolean(resultSet.getInt(12));
                        profSubj1 = resultSet.getInt(13);
                        profSubj2 = resultSet.getInt(14);
                        subj3 = resultSet.getInt(15);
                        certificate = resultSet.getDouble(16);
                        isAgreeForPayment = convertIntToBoolean(resultSet.getInt(16));
                        setData(surname, name, patronymic, passport, address, dateOfBirth,
                                phoneNumber, educationForm, isAimed, isWithoutExams, isBudget,
                                isOutOfCompetitions, profSubj1, profSubj2, subj3,
                                certificate, isAgreeForPayment);
                    }
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception) {
            ConnectionResult = exception.getMessage();
        }
    }


    @SuppressLint("SetTextI18n")
    public void setData(String surname, String name, String patronymic, String passport,
                        String address, String dateOfBirth, String phoneNumber,
                        String educationForm, boolean isAimed, boolean isWithoutExams,
                        boolean isBudget, boolean isOutOfCompetitions, int profSubj1, int profSubj2,
                        int subj3, double certificate, boolean isAgreeForPayment) {
        surnameT.setText(surname);
        nameT.setText(name);
        patronymicT.setText(patronymic);
        passportT.setText(passport);
        addressT.setText(address);
        dateOfBirthT.setText(dateOfBirth);
        phoneNumberT.setText(phoneNumber);
        educationFormT.setText(educationForm);
        isAimedC.setChecked(isAimed);
        isWithoutExamsC.setChecked(isWithoutExams);
        isBudgetC.setChecked(isBudget);
        isOutOfCompetitionsC.setChecked(isOutOfCompetitions);
        profSubj1T.setText(Integer.toString(profSubj1));
        profSubj2T.setText(Integer.toString(profSubj2));
        subj3T.setText(Integer.toString(subj3));
        certificateT.setText(Double.toString(certificate));
        isAgreeForPaymentC.setChecked(isAgreeForPayment);
    }

    public boolean convertIntToBoolean(int intValue) {
        return intValue > 0;
    }
}