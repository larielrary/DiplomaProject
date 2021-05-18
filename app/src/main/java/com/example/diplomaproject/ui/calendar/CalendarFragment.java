package com.example.diplomaproject.ui.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;
import com.example.diplomaproject.data.database.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class CalendarFragment extends Fragment{

    Connection connection;
    CalendarView calendarView;
    String ConnectionResult;
    TextView eventTitle;
    TextView eventURL;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarView = root.findViewById(R.id.calendarView);
        eventTitle = root.findViewById(R.id.eventTitle);
        eventURL = root.findViewById(R.id.eventURL);

        fillDates();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // display the selected date by using a toast
            fillCalendar(year + "-0" + (month + 1) + "-0" + dayOfMonth);
            //Toast.makeText(root.getContext(), dayOfMonth + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

        });

        return root;
    }

    public void fillDates(){
        String date;
        ArrayList<String> dates = new ArrayList<>();
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "SELECT EventDate FROM EVENT";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null) {
                    while (resultSet.next()) {
                        date = resultSet.getString("EventDate");
                        dates.add(date);
                    }
                    setDate(dates);
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception){
            ConnectionResult = exception.getMessage();
        }
    }

    @SuppressLint("SimpleDateFormat")
    public void setDate(ArrayList<String> dates) throws ParseException {
        for (String date: dates) {
            calendarView.setDate(Objects.requireNonNull(new SimpleDateFormat("yyyy-MM-dd")
                    .parse(date)).getTime(), true, true);
        }
    }

    public void fillCalendar(String date){
        String title;
        String url;
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connection();
            if (connection != null) {
                String query = "SELECT EventTitle, EventUrl FROM EVENT WHERE EventDate = '"
                        + date + "'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                if(resultSet != null) {
                    while (resultSet.next()) {
                        title = resultSet.getString("EventTitle");
                        url = resultSet.getString("EventUrl");
                        setEvent(title, url);
                    }
                }
            } else {
                ConnectionResult = "Check Connection";
            }
        } catch (Exception exception){
            ConnectionResult = exception.getMessage();
        }
    }

    public void setEvent(String title, String url){
        eventTitle.setText(title);
        eventURL.setText(url);
    }
}
