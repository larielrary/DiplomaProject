package com.example.diplomaproject.ui.speciality;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;
import com.example.diplomaproject.data.database.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SpecialityInfoFragment extends Fragment {

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_speciality_info, container, false);
        faisListView = root.findViewById(R.id.faisListView);
        gefListView = root.findViewById(R.id.gefListView);
        mtfListView = root.findViewById(R.id.mtfListView);
        msfListView = root.findViewById(R.id.msfListView);
        efListView = root.findViewById(R.id.efListView);

        faisTextView = root.findViewById(R.id.faisTextView);
        gefTextView = root.findViewById(R.id.gefTextView);
        mtfTextView = root.findViewById(R.id.mtfTextView);
        msfTextView = root.findViewById(R.id.msfTextView);
        efTextView = root.findViewById(R.id.efTextView);

        setupHyperlink();

        return root;
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