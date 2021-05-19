package com.example.diplomaproject.ui.sendemail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;

public class SendMailFragment extends Fragment implements View.OnClickListener{

    EditText editTextSubject, editTextMessage;
    Button send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_send_mail, container, false);

        editTextSubject = root.findViewById(R.id.subjEditText);
        editTextMessage = root.findViewById(R.id.messageEditText);
        send = root.findViewById(R.id.sendEmailButton);

        send.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();

        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ Utils.EMAIL});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Выберите почтовый клиент:"));
        editTextMessage.setText("");
        editTextSubject.setText("");
    }
}