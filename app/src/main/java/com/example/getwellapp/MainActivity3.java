package com.example.getwellapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private Spinner etFrequency;
    Button btnSave;
    dbHelper databaseHelper;
    int prescriptionId;
    EditText etName, etDose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        databaseHelper = new dbHelper(this);
        etName = findViewById(R.id.editTextText);
        etDose = findViewById(R.id.editTextDate);
        etFrequency = findViewById(R.id.editTextDateEnd);
        btnSave = findViewById(R.id.button);



        String[] items = {"DAY", "MONTH", "WEEK", "YEAR", "HOUR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        etFrequency.setAdapter(adapter);


        prescriptionId = getIntent().getIntExtra("prescriptionId", -1);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String dose = etDose.getText().toString();
                String frequency = etFrequency.getSelectedItem().toString();

                if (databaseHelper.addMedicine(prescriptionId, name, dose, frequency)) {
                    Toast.makeText(MainActivity3.this, "Medicine added", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(MainActivity3.this, "Failed to add medicine", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}