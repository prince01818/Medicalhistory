package com.example.sabbir.medicalhistory;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
   EditText showDate;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showDate = (EditText) findViewById(R.id.dateET);
        showDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "datePicker");
            }

        });
    }
    public void submit (View v) {
        if (SaveData()) {
            Intent intent = new Intent(getBaseContext(), MedicalHistory.class);
            startActivity(intent);
        }
    }


    private boolean SaveData() {
        final EditText doctor_name = (EditText) findViewById(R.id.doctor_nameET);

        final EditText specialized = (EditText) findViewById(R.id.specializedET);

        final EditText date = (EditText) findViewById(R.id.dateET);

        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        AlertDialog ad = adb.create();


        if (doctor_name.getText().length() == 0)

        {

            ad.setMessage("Please input [DOCTOR_NAME] ");

            ad.show();

            doctor_name.requestFocus();

            return false;

        }

        if (specialized.getText().length() == 0)

        {

            ad.setMessage("Please input [Sepcialized on] ");

            ad.show();

            specialized.requestFocus();

            return false;

        }

        if (date.getText().length() == 0)

        {

            ad.setMessage("Please input [DATE] ");

            ad.show();

            date.requestFocus();

            return false;


        }
        final iCareDB Db = new iCareDB(this);
        long saveStatus = Db.InsertData(doctor_name.getText().toString(), specialized.getText().toString(), date.getText().toString());


        if (saveStatus <= 0)

        {

            ad.setMessage("Error!! ");

            ad.show();

            return false;

        }

        Toast.makeText(MainActivity.this, "Add Data Successfully. ",

                Toast.LENGTH_SHORT).show();

        return true;

    }


}


