package com.example.sabbir.medicalhistory;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.AlertDialog;
import android.content.Intent;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sabbir.medicalhistory.R;
import com.example.sabbir.medicalhistory.iCareDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes;

public class MedicalHistory extends AppCompatActivity {
    private ArrayAdapter<String> adapter;        // The list adapter
    private String selectedItem;                // Stores the selected list item
    private final Context context = this;        // This context

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        final iCareDB Db = new iCareDB(this);

        final ArrayList<HashMap<String, String>> MebmerList = Db.SelectAllData();

        final ListView lisView1 = (ListView) findViewById(R.id.listLV);


        final SimpleAdapter sAdap;

        sAdap = new SimpleAdapter(MedicalHistory.this, MebmerList, R.layout.historyshow, new String[]{"DOCTOR_NAME", "SPECIALIZED", "DATE"}, new int[]{R.id.nameTV, R.id.specializedTV, R.id.dateTV});
        lisView1.setAdapter(sAdap);
        //..........


        lisView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> arg0,
                                           View arg1, final int poition, final long id) {
                final AlertDialog.Builder b = new AlertDialog.Builder(
                        MedicalHistory.this);
                b.setIcon(android.R.drawable.ic_dialog_alert);
                b.setMessage("Delete this from history?");
                b.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                                iCareDB db = new iCareDB(getApplicationContext());
                                        Db.delete(id+"");
                                        MebmerList.remove(poition);
                                        adapter.notifyDataSetChanged();
                                    }
                                });
                                Toast.makeText(getApplicationContext(),
                                        "Yes", Toast.LENGTH_LONG)
                                        .show();
                            }
                        });
                b.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                dialog.cancel();
                            }
                        });

                b.show();
                return true;
            }
        });


        //........
        final Button save = (Button) findViewById(R.id.btADD);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent newActivity = new Intent(getBaseContext(), MainActivity.class);

                startActivity(newActivity);


            }

        });

    }
}