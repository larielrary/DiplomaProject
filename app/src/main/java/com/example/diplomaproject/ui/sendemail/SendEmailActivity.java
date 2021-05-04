package com.example.diplomaproject.ui.sendemail;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomaproject.MainActivity;
import com.example.diplomaproject.R;

public class SendEmailActivity extends AppCompatActivity {

    EditText editTextSubject, editTextMessage;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        editTextSubject = (EditText)findViewById(R.id.subjEditText);
        editTextMessage = (EditText)findViewById(R.id.messageEditText);

        send = (Button)findViewById(R.id.sendEmailButton);

        send.setOnClickListener(arg0 -> {
            String subject = editTextSubject.getText().toString();
            String message = editTextMessage.getText().toString();


            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ Utils.EMAIL});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.putExtra(Intent.EXTRA_TEXT, message);

            //need this to prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "Выберите почтовый клиент:"));
        });
    }

    public void onEmailBackButtonClick(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        closeActivity();
    }

    private void closeActivity() {
        this.finish();
    }


}