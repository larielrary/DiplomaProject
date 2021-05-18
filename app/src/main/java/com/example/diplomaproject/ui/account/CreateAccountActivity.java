package com.example.diplomaproject.ui.account;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomaproject.R;
import com.example.diplomaproject.data.database.ConnectionHelper;
import com.example.diplomaproject.ui.dialog.Creatable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CreateAccountActivity extends AppCompatActivity implements Creatable {

    Spinner spinner;
    String[] paths = {"item 1", "item 2", "item 3"};
    String ConnectionResult;
    Connection connection;
    int USER_ID;
    final String OLD_FORMAT = "dd MMM yyyy г.";
    final String NEW_FORMAT = "yyyy-MM-dd";

    TextView currentDate;
    Calendar date = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        spinner = findViewById(R.id.EducationForm);

        currentDate = findViewById(R.id.DateOfBirth);
        setInitialDate();
        fillSpinner();
        //spinner.setOnItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        USER_ID = intent.getIntExtra("userId", 0);
    }

    public void fillSpinner(){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "SELECT Name FROM EDUCATIONFORM";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null){
                    ArrayList<String> data = new ArrayList<>();
                    while (resultSet.next()){
                        String name = resultSet.getString("Name");
                        data.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                            android.R.layout.simple_spinner_item, data);
                    spinner.setAdapter(arrayAdapter);
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception){
            ConnectionResult = exception.getMessage();
        }
    }

    public void onCreateModelButtonClick(View view) {
        try {
            //databaseAdapter = new DatabaseAdapter(this.getApplicationContext());
            String surname = ((EditText)findViewById(R.id.Surname)).getText().toString();
            String name = ((EditText)findViewById(R.id.Name)).getText().toString();
            String patronymic = ((EditText)findViewById(R.id.Patronymic)).getText().toString();
            String passport = ((EditText)findViewById(R.id.Passport)).getText().toString();
            String address = ((EditText)findViewById(R.id.Address)).getText().toString();

            String oldDateString = ((TextView)findViewById(R.id.DateOfBirth)).getText().toString();  // where startDate is your TextView
            String newDateString;

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(OLD_FORMAT);
            Date date = simpleDateFormat.parse(oldDateString);
            simpleDateFormat.applyPattern(NEW_FORMAT);
            newDateString = simpleDateFormat.format(date);
            Date newDate = simpleDateFormat.parse(newDateString);
            java.sql.Date sqlDate = new java.sql.Date(newDate.getTime());
            String phone = ((EditText)findViewById(R.id.PhoneNumber)).getText().toString();

            String educationForm = spinner.getSelectedItem().toString();

            boolean isAimed = ((CheckBox) findViewById(R.id.IsAimed)).isChecked();
            boolean isWithoutExams = ((CheckBox) findViewById(R.id.IsWithoutExams)).isChecked();
            boolean isBudget = ((CheckBox) findViewById(R.id.IsBudget)).isChecked();
            boolean isOutOfCompetitions = ((CheckBox) findViewById(R.id.IsOutOfCompetitions)).isChecked();

            String profSubj1EE = ((EditText)findViewById(R.id.ProfSubj1EE)).getText().toString();
            String profSubj2EE = ((EditText)findViewById(R.id.ProfSubj2EE)).getText().toString();
            String subj3EE = ((EditText)findViewById(R.id.Subj3EE)).getText().toString();
            String certificateScoreX10 = ((EditText)findViewById(R.id.CertificateScoreX10)).getText().toString();

            boolean isAgreeForPayment = ((CheckBox) findViewById(R.id.IsAgreeForPayment)).isChecked();


            /*if(fio.length() != 0 && address.length() != 0) {

            }*/

            try {
                ConnectionHelper connectionHelper = new ConnectionHelper();
                connection = connectionHelper.connection();
                if (connection != null) {
                    String query = "INSERT INTO ENROLLEE VALUES (" + USER_ID + ", N'" + surname + "', N'" +
                            name + "', N'" + patronymic + "', N'" + passport + "', N'" + address +
                            "', '" + sqlDate + "', '" + phone + "', " + getFormId(educationForm) +
                            ", " + convertBooleanToInt(isAimed) + ", " +
                            convertBooleanToInt(isWithoutExams) + ", " +
                            convertBooleanToInt(isBudget) + ", " +
                            convertBooleanToInt(isOutOfCompetitions) + ", " +
                            Integer.parseInt(profSubj1EE) + ", " + Integer.parseInt(profSubj2EE) +
                            ", " + Integer.parseInt(subj3EE)
                            + ", " + Double.parseDouble(certificateScoreX10) + ", " +
                            convertBooleanToInt(isAgreeForPayment) + ")";
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query);

                } else {
                    ConnectionResult = "Check Connection";
                }
            } catch (Exception exception){
                ConnectionResult = exception.getMessage();
            }




        }
        catch (Exception exception) { }
        Intent intent = new Intent(this, ViewAccountActivity.class);
        startActivity(intent);
    }

    public int getFormId(String formName){
        int id = 0;
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "SELECT * FROM EDUCATIONFORM WHERE Name=N'" + formName + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null) {
                    while (resultSet.next()) {
                        id = resultSet.getInt("FormId");
                    }
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception){
            ConnectionResult = exception.getMessage();
        }
        return id;
    }

    public int convertBooleanToInt(boolean boolValue){
        return boolValue ? 1 : 0;
    }


    public void onSetDateBtnClick(View view){
        new DatePickerDialog(CreateAccountActivity.this, d,
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    private void setInitialDate() {

        currentDate.setText(DateUtils.formatDateTime(this,
                date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, monthOfYear);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };
}