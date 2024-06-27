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

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private Spinner spinner;
    Button saveB;
    private ListView medicineListView;
    private Button addMedicineButton;
    private List<medicin> medicineList;
    private MedicinAdapter medicineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);

        saveB = findViewById(R.id.button);
        spinner = findViewById(R.id.editTextDateEnd);


        String[] items = {"DAY", "MONTH", "WEEK", "YEAR", "HOUR"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);


        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity3.this);
                builder.setTitle("Add Medicine");

                View view = LayoutInflater.from(MainActivity3.this).inflate(R.layout.activity_main3, null);
                builder.setView(view);

                final EditText nameEditText = view.findViewById(R.id.editTextText);
                final EditText amountTakenEditText = view.findViewById(R.id.editTextDate);
                final Spinner frequencyEditText = view.findViewById(R.id.editTextDateEnd);

                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameEditText.getText().toString();
                        String amountTaken = amountTakenEditText.getText().toString();
                        String frequency = frequencyEditText.getSelectedItem().toString();

                        medicin medicine = new medicin(name, amountTaken, frequency);
                        medicineList.add(medicine);
                        medicineAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.show();
            }
        });
    }
}