package com.example.societymemberregistration;

import android.annotation.SuppressLint;
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

public class DatePickerDialog extends DialogFragment {

    DatePicker datePicker;
    String SelectedDate;
    Button btnOK, btnCancel;

    public DatePickerDialog() {
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_picker_dialog, container, false);
        datePicker = view.findViewById(R.id.datePicker);

        btnOK = view.findViewById(R.id.btnOK);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StringBuilder builder = new StringBuilder();
                builder.append(datePicker.getDayOfMonth() + "/");
                builder.append(datePicker.getMonth() + 1 + "/");
                builder.append(datePicker.getYear());
                SelectedDate = builder.toString();

                ((RegistrationMainActivity) getActivity()).SetDate(SelectedDate);
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

