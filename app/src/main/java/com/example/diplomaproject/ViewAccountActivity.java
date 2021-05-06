package com.example.diplomaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ViewAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
    }

    public void onBackButtonClick(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        closeActivity();
    }

    private void closeActivity() {
        this.finish();
    }
}