package com.example.sabbir.medicalhistory;

/**
 * Created by SABBIR on 1/23/2016.
 */
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        Toast.makeText(getActivity(),year+" "+(month+1)+" "+day,Toast.LENGTH_LONG).show();

        String time=Integer.toString(day)+"-"+Integer.toString(month+1)+"-"+Integer.toString(year);
        EditText showDate=(EditText)getActivity().findViewById(R.id.dateET);


        showDate.setText(time);

    }
}

