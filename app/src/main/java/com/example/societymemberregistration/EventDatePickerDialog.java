package com.example.societymemberregistration;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;

import com.example.societymemberregistration.Activity.RegistrationMainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventDatePickerDialog extends DialogFragment {

    DatePicker eventDatePicker;
    String SelectedEventDate;
    Button btnOK, btnCancel;

    public EventDatePickerDialog() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_date_picker_dialog, container, false);
        eventDatePicker = view.findViewById(R.id.eventDatePicker);

        Calendar calendar = Calendar.getInstance();

        eventDatePicker.setMinDate(calendar.getTimeInMillis());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            eventDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Calendar selectedDateCalendar = Calendar.getInstance();
                    selectedDateCalendar.set(year,month,dayOfMonth);

                    SelectedEventDate = sdf.format(selectedDateCalendar.getTime());
                }
            });
        } else {
            CalendarView calendarView = eventDatePicker.getCalendarView();
            calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                    // Compare the selected date with the current date
                    Calendar selectedDateCalendar = Calendar.getInstance();
                    selectedDateCalendar.set(year, month, dayOfMonth);

                    if (selectedDateCalendar.after(calendar)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        SelectedEventDate = sdf.format(selectedDateCalendar.getTime());
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
                builder.append(eventDatePicker.getDayOfMonth() + "/");
                builder.append(eventDatePicker.getMonth() + 1 + "/");
                builder.append(eventDatePicker.getYear());
                SelectedEventDate = builder.toString();
                ((RegistrationMainActivity) getActivity()).SetEventDate(SelectedEventDate);
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