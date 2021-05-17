package com.example.diplomaproject.ui.speciality;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diplomaproject.R;
import com.example.diplomaproject.data.database.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SpecialityActivity extends AppCompatActivity {

    ListView faisListView;
    ListView gefListView;
    ListView mtfListView;
    ListView msfListView;
    ListView efListView;

    TextView faisTextView;
    TextView gefTextView;
    TextView mtfTextView;
    TextView msfTextView;
    TextView efTextView;
    String ConnectionResult="";
    Connection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speciality);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        faisListView = findViewById(R.id.faisListView);
        gefListView = findViewById(R.id.gefListView);
        mtfListView = findViewById(R.id.mtfListView);
        msfListView = findViewById(R.id.msfListView);
        efListView = findViewById(R.id.efListView);

        faisTextView = findViewById(R.id.faisTextView);
        gefTextView = findViewById(R.id.gefTextView);
        mtfTextView = findViewById(R.id.mtfTextView);
        msfTextView = findViewById(R.id.msfTextView);
        efTextView = findViewById(R.id.efTextView);
        setupHyperlink();
    }

    public void getSpetialities(){
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "SELECT s.Name FROM SPECIALITY s JOIN FACULTY f ON" +
                        "f.FacultyID = s.FacultyID WHERE f.Name='" + faisTextView.getText() + "'";
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
    private void setupHyperlink() {
        faisTextView.setMovementMethod(LinkMovementMethod.getInstance());
        gefTextView.setMovementMethod(LinkMovementMethod.getInstance());
        mtfTextView.setMovementMethod(LinkMovementMethod.getInstance());
        msfTextView.setMovementMethod(LinkMovementMethod.getInstance());
        efTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

}