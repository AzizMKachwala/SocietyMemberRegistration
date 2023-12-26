package com.example.societymemberregistration.Activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.societymemberregistration.DateOfBirthPickerDialog;
import com.example.societymemberregistration.DatePickerDialog;
import com.example.societymemberregistration.EventDatePickerDialog;
import com.example.societymemberregistration.R;

public class RegistrationMainActivity extends AppCompatActivity implements TimePickerDialog.TimeClick {

    EditText etvFirstName, etvLastName, etvSpinnerUnit, etvRecyclerViewUnit, etvDatePicker, etvDateOfBirthPicker, etvEventDatePicker, etvTimePicker;
    RadioGroup genderRadioGroup;
    RadioButton selectedRadioButton;
    Button btnRegister, btnReset;

    private ActivityResultLauncher<Intent> spinnerUnitLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_main);

        etvFirstName = findViewById(R.id.etvFirstName);
        etvLastName = findViewById(R.id.etvLastName);
        etvSpinnerUnit = findViewById(R.id.etvSpinnerUnit);
//        etvRecyclerViewUnit = findViewById(R.id.etvRecyclerViewUnit);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        btnRegister = findViewById(R.id.btnRegister);
        btnReset = findViewById(R.id.btnReset);

        spinnerUnitLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            if (result.getData() != null) {
                                String resultData = result.getData().getStringExtra("result_spinner");
                                etvSpinnerUnit.setText(resultData);
                            }
                        } else if (result.getResultCode() == RESULT_CANCELED) {
                            etvSpinnerUnit.setText("Select Unit");
                        }
                    }
                }
        );

        etvSpinnerUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationMainActivity.this, SpinnerUnitActivity.class);
                spinnerUnitLauncher.launch(intent);
            }
        });

//        etvRecyclerViewUnit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(RegistrationMainActivity.this, NestedRecyclerViewUnitActivity.class );
//                startActivity(intent);
//            }
//        });

        etvDatePicker = findViewById(R.id.etvDatePicker);
        etvTimePicker = findViewById(R.id.etvTimePicker);
        etvDateOfBirthPicker = findViewById(R.id.etvDateOfBirthPicker);
        etvEventDatePicker = findViewById(R.id.etvEventDatePicker);

        etvEventDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                EventDatePickerDialog eventDatePickerDialog = new EventDatePickerDialog();
                eventDatePickerDialog.setCancelable(false);
                eventDatePickerDialog.show(fragmentTransaction, "eventDatePicker");
            }
        });

        etvDateOfBirthPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DateOfBirthPickerDialog dateOfBirthPickerDialog = new DateOfBirthPickerDialog();
                dateOfBirthPickerDialog.setCancelable(false);
                dateOfBirthPickerDialog.show(fragmentTransaction, "dateOfBirthPicker");
            }
        });

        etvDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.setCancelable(false);
                datePickerDialog.show(fragmentTransaction, "datePicker");
            }
        });

        etvTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                TimePickerDialog timePickerDialog = new TimePickerDialog();
                timePickerDialog.setCancelable(false);
                timePickerDialog.show(fragmentTransaction, "timePicker");
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int selectedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                    String gender = selectedRadioButton.getText().toString();

                    String firstName = etvFirstName.getText().toString().trim();
                    String lastName = etvLastName.getText().toString().trim();
                    String spinnerUnit = etvSpinnerUnit.getText().toString().trim();
//                    String recyclerViewUnit = etvRecyclerViewUnit.getText().toString().trim();
                    String date = etvDatePicker.getText().toString().trim();
                    String dateOfBirth = etvDateOfBirthPicker.getText().toString().trim();
                    String eventDate = etvEventDatePicker.getText().toString().trim();
                    String time = etvTimePicker.getText().toString().trim();

                    if (!firstName.isEmpty() && !lastName.isEmpty() && !spinnerUnit.isEmpty() && !date.isEmpty() && !time.isEmpty()) {
                        Intent intent = new Intent(RegistrationMainActivity.this, ResultActivity.class);
                        intent.putExtra("firstName", firstName);
                        intent.putExtra("lastName", lastName);
                        intent.putExtra("spinnerUnit", spinnerUnit);
//                        intent.putExtra("recyclerViewUnit", recyclerViewUnit);
                        intent.putExtra("date", date);
                        intent.putExtra("dateOfBirth", dateOfBirth);
                        intent.putExtra("eventDate", eventDate);
                        intent.putExtra("time", time);
                        intent.putExtra("gender", gender);

                        startActivity(intent);
                    } else {
                        Toast.makeText(RegistrationMainActivity.this, "Please Enter First Name, Last Name, Spinner Unit, Date and Time", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(RegistrationMainActivity.this, "Please Enter All the Required Details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etvFirstName.setText("");
                etvLastName.setText("");
                etvSpinnerUnit.setText("");
//                etvRecyclerViewUnit.setText("");
                etvDatePicker.setText("");
                etvDateOfBirthPicker.setText("");
                etvEventDatePicker.setText("");
                etvTimePicker.setText("");
                genderRadioGroup.clearCheck();
            }
        });
    }

    public void onRoomItemClick(String roomNumber) {
        etvRecyclerViewUnit.setText(roomNumber);
    }

    public void SetDate(String selectedDate) {
        etvDatePicker.setText(selectedDate);
    }

    @Override
    public void onTimeItemClick(String SelectedTime) {
        etvTimePicker.setText(SelectedTime);
    }

    public void SetBirthDate(String selectedBirthDate) {
        etvDateOfBirthPicker.setText(selectedBirthDate);
    }

    public void SetEventDate(String selectedEventDate) {
        etvEventDatePicker.setText(selectedEventDate);
    }
}
