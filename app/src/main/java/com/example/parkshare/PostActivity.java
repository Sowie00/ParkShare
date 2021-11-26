package com.example.parkshare;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {
    private Button selectAvailabilityButton;

    private EditText startDate;
    private EditText endDate;
    private EditText startTime;
    private EditText endTime;

    private String startDateString;
    private String endDateString;
    private String startTimeString;
    private String endTimeString;
    public ArrayList<String> startDates = new ArrayList<String>();
    public ArrayList<String> endDates = new ArrayList<String>();

    public ArrayList<String> startTimes = new ArrayList<String>();
    public ArrayList<String> endTimes = new ArrayList<String>();
    protected static final String ACTIVITY_NAME = "PostActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        Log.i(ACTIVITY_NAME, "In onCreate()");
    }

    public void selectAvailabilityButtonClicked(View v) {

        AlertDialog.Builder custom = new AlertDialog.Builder(PostActivity.this);
        LayoutInflater inflater = PostActivity.this.getLayoutInflater();
        View view2 = inflater.inflate(R.layout.dialog_calendar, null);
        custom.setView(view2);

        startDate = findViewById(R.id.startDateEditText);
        endDate = (EditText)v.findViewById(R.id.endDateEditText);
        startTime = (EditText)v.findViewById(R.id.startTimeEditText);
        endTime = (EditText)v.findViewById(R.id.endTimeEditText);

        custom.setPositiveButton(R.string.doneCalendar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                startDateString = startDate.getText().toString();
                endDateString = endDate.getText().toString();
                startTimeString = startTime.getText().toString();
                endTimeString = endTime.getText().toString();

                startDates.add(startDateString);
                startTimes.add(startTimeString);
                endDates.add(endDateString);
                endTimes.add(endTimeString);
                Toast toast = Toast.makeText(getApplicationContext(),String.valueOf(startDates), Toast.LENGTH_LONG);
                toast.show();
            }
        });

        custom.setNegativeButton(R.string.cancelCalendar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //user cancelled the dialog
            }
        });

        AlertDialog customdialog = custom.create();
        custom.show();

    }
    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu m){
        getMenuInflater().inflate(R.menu.toolbar_menu, m );
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem mi) {
        int mi_id = mi.getItemId();
        switch (mi_id) {

            case R.id.settings:
                Log.d("Toolbar", "About Selected");
                //need to display the author’s name, Activity version number, and instructions for how to use the interface
                AlertDialog.Builder custom = new AlertDialog.Builder(PostActivity.this);
                // Get the layout inflater
                LayoutInflater inflater = PostActivity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_help, null);
                custom.setView(view);

                custom.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

                AlertDialog customdialog = custom.create();
                custom.show();
                break;

        }
        return true;
    }
}

