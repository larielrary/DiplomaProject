package com.example.diplomaproject.ui.account;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomaproject.MainActivity;
import com.example.diplomaproject.R;
import com.example.diplomaproject.ui.dialog.Creatable;

import java.util.Calendar;

public class CreateAccountActivity extends AppCompatActivity implements Creatable, AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String[] paths = {"item 1", "item 2", "item 3"};
    TextView currentDate;
    Calendar date = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        spinner = findViewById(R.id.EducationForm);
        currentDate = findViewById(R.id.DateOfBirth);
        setInitialDate();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onCreateModelButtonClick(View view) {
        try {

            /*databaseAdapter = new DatabaseAdapter(this.getApplicationContext());
            int id = Integer.parseInt(((EditText)findViewById(R.id.Id)).getText().toString());
            String fio = ((EditText)findViewById(R.id.Surname)).getText().toString();
            String address = ((EditText)findViewById(R.id.Name)).getText().toString();
            int sum = Integer.parseInt(((EditText)findViewById(R.id.MiddleName)).getText().toString());
            int date = Integer.parseInt(((EditText)findViewById(R.id.Address)).getText().toString());
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.INSURED_ID, id);
            values.put(DatabaseHelper.INSURED_FIO, fio);
            values.put(DatabaseHelper.INSURED_ADDRESS, address);
            values.put(DatabaseHelper.INSURED_SUM, sum);
            values.put(DatabaseHelper.INSURED_DATE, date);
            if(fio.length() != 0 && address.length() != 0) {
                Enrollee customer = new Enrollee(id, fio, address, sum, date);
                databaseAdapter.Open();
                databaseAdapter.InsuredInsert(customer);
                databaseAdapter.Close();
            }*/
        }
        catch (Exception exception) { }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the thrid item gets selected
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

}