package com.example.societymemberregistration;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.societymemberregistration.Activity.RegistrationMainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateOfBirthPickerDialog extends DialogFragment {

    DatePicker dateOfBirthPicker;
    String SelectedBirthDate;
    Button btnOK, btnCancel;

    public DateOfBirthPickerDialog() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_of_birth_picker_dialog, container, false);
        dateOfBirthPicker = view.findViewById(R.id.dateOfBirthPicker);

        Calendar calendar = Calendar.getInstance();

        dateOfBirthPicker.setMaxDate(calendar.getTimeInMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dateOfBirthPicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar selectedDateCalendar = Calendar.getInstance();
                    selectedDateCalendar.set(year, month, dayOfMonth);

                    SelectedBirthDate = sdf.format(selectedDateCalendar.getTime());
                }
            });
        } else {
            CalendarView calendarView = dateOfBirthPicker.getCalendarView();
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                    Calendar selectedDateCalendar = Calendar.getInstance();
                    selectedDateCalendar.set(year, month, dayOfMonth);

                    if (selectedDateCalendar.before(calendar)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        SelectedBirthDate = sdf.format(selectedDateCalendar.getTime());
                    } else {

                    }
                }
            });
        }

        btnOK = view.findViewById(R.id.btnOK);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder builder = new StringBuilder();
                builder.append(dateOfBirthPicker.getDayOfMonth() + "/");
                builder.append(dateOfBirthPicker.getMonth() + 1 + "/");
                builder.append(dateOfBirthPicker.getYear());
                SelectedBirthDate = builder.toString();
                ((RegistrationMainActivity) getActivity()).SetBirthDate(SelectedBirthDate);
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }
}