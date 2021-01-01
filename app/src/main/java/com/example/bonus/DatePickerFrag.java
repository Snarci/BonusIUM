package com.example.bonus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFrag extends DialogFragment {
    private Calendar date;

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }


    public static DatePickerFrag newInstance() {
        DatePickerFrag frag = new DatePickerFrag();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final DatePicker datePicker =new DatePicker(getActivity());
        date =Calendar.getInstance();
        return new AlertDialog.Builder(getActivity())
                .setView(datePicker)
                .setPositiveButton("Conferma",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                date.set(Calendar.YEAR,datePicker.getYear());
                                date.set(Calendar.MONTH,datePicker.getMonth());
                                date.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                                ((Registrazione)getActivity()).updateDataNascita(date);
                                ((Registrazione)getActivity()).doPositiveClick(date);
                            }
                        }
                )
                .setNegativeButton("Cancella",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((Registrazione)getActivity()).doNegativeClick(getDialog());
                            }
                        }
                )
                .create();
    }
}

