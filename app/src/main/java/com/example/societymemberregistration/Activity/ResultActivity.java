package com.example.societymemberregistration.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.societymemberregistration.R;

public class ResultActivity extends AppCompatActivity {

    TextView txtFirstNameResult, txtLastNameResult, txtSpinnerUnitResult, txtRecyclerViewUnitResult, txtDate, txtDateOfBirthPicker, txtEventDatePicker, txtTime, txtGenderResult;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtFirstNameResult = findViewById(R.id.txtFirstNameResult);
        txtLastNameResult = findViewById(R.id.txtLastNameResult);
        txtSpinnerUnitResult = findViewById(R.id.txtSpinnerUnitResult);
//        txtRecyclerViewUnitResult = findViewById(R.id.txtRecyclerViewUnitResult);
        txtGenderResult = findViewById(R.id.txtGenderResult);
        txtDate = findViewById(R.id.txtDate);
        txtDateOfBirthPicker = findViewById(R.id.txtDateOfBirthPicker);
        txtEventDatePicker = findViewById(R.id.txtEventDatePicker);
        txtTime = findViewById(R.id.txtTime);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String spinnerUnit = intent.getStringExtra("spinnerUnit");
//        String recyclerUnit = intent.getStringExtra("recyclerViewUnit");
        String date = intent.getStringExtra("date");
        String dateOfBirth = intent.getStringExtra("dateOfBirth");
        String eventDate = intent.getStringExtra("eventDate");
        String time = intent.getStringExtra("time");
        String gender = intent.getStringExtra("gender");

        txtFirstNameResult.setText("First Name: " + firstName);
        txtLastNameResult.setText("Last Name: " + lastName);
        txtSpinnerUnitResult.setText("Spinner Unit: " + spinnerUnit);
//        txtRecyclerViewUnitResult.setText("Nested RecyclerView Unit: " + recyclerUnit);
        txtDate.setText("Date: " + date);
        txtDateOfBirthPicker.setText("Date Of Birth: " + dateOfBirth);
        txtEventDatePicker.setText("Event Date: " + eventDate);
        txtTime.setText("Time:" + time);
        txtGenderResult.setText("Gender: " + gender);
    }
}