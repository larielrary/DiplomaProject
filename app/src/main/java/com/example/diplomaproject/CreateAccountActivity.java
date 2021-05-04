package com.example.diplomaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.diplomaproject.ui.dialog.Creatable;

public class CreateAccountActivity extends AppCompatActivity implements Creatable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
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

    public void onBackButtonClick(View view) {
    }
}