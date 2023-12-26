package com.example.societymemberregistration;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.societymemberregistration.Activity.RegistrationMainActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimePickerDialog extends DialogFragment {

    TimePicker timePicker;
    String SelectedTime;
    Button btnOK,btnCancel;
    SimpleDateFormat sdf;
    Calendar selectedTime;

    TimeClick timeClick;
    public interface TimeClick{
        void onTimeItemClick(String SelectedTime);
    }

    public TimePickerDialog() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_picker_dialog, container, false);
        timePicker = view.findViewById(R.id.timePicker);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
//                StringBuilder builder = new StringBuilder();
//                builder.append(timePicker.getHour() + ":");
//                builder.append(timePicker.getMinute());
//                SelectedTime = builder.toString();

//                Calendar selectedTime = Calendar.getInstance();
//                selectedTime.set(Calendar.HOUR_OF_DAY, hour);
//                selectedTime.set(Calendar.MINUTE, minute);
//                sdf = new SimpleDateFormat("hh:mm a", Locale.getDefault());
//                SelectedTime = sdf.format(selectedTime.getTime());
            }
        });

        btnOK = view.findViewById(R.id.btnOK);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ((RegistrationMainActivity)getActivity()).SetTime(SelectedTime);
//                dismiss();

//                String selectedTime = sdf.format(selectedTime.getTime());
//                ((TimeClick) getActivity()).onTimeItemClick(selectedTime);
//                dismiss();

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                String amPm;
                if (hour >= 12) {
                    amPm = "PM";
                    if (hour > 12) {
                        hour -= 12;
                    }
                } else {
                    amPm = "AM";
                    if (hour == 0) {
                        hour = 12;
                    }
                }

                StringBuilder builder = new StringBuilder();
                builder.append(String.format("%02d:%02d %s", hour, minute,amPm));
                String formattedTime = builder.toString();
                ((TimeClick) getActivity()).onTimeItemClick(formattedTime);
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