package com.example.diplomaproject.ui.calculation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomaproject.R;

public class CalculationActivity extends AppCompatActivity {
    Button calculateBtn;
    EditText subj1;
    EditText subj2;
    EditText subj3;
    EditText certificate;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @SuppressLint("SetTextI18n")
    public void onCalculateBtnClick (View view){
        subj1 = findViewById(R.id.subj1Edit);
        subj2 = findViewById(R.id.subj2Edit);
        subj3 = findViewById(R.id.subj3Edit);
        certificate = findViewById(R.id.certificateEdit);
        result = findViewById(R.id.resultText);
        calculateBtn = findViewById(R.id.calculateBtn);
        int res = Integer.parseInt(subj1.getText().toString())+
                Integer.parseInt(subj2.getText().toString())+
                Integer.parseInt(subj3.getText().toString())+
                Integer.parseInt(certificate.getText().toString());
        result.setText(Integer.toString(res));
    }

}
