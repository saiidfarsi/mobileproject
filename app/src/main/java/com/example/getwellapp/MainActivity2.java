package com.example.getwellapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    EditText startdate, enddate, preName;

    Button addpres;
    dbHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        startdate = (EditText) findViewById(R.id.editTextDate);
        enddate = (EditText) findViewById(R.id.editTextDateEnd);
        preName = (EditText) findViewById(R.id.editTextText);
        addpres = (Button) findViewById(R.id.button);
        databaseHelper = new dbHelper(this);

        addpres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = preName.getText().toString();
                String startDate = startdate.getText().toString();
                String endDate = enddate.getText().toString();

                if (databaseHelper.addPrescription(name, startDate, endDate)) {
                    Toast.makeText(MainActivity2.this, "Prescription added", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity2.this, "Failed to add prescription", Toast.LENGTH_SHORT).show();
                }
            }
        });




        addpres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mec = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(mec);
            }
        });

    }
}