package com.example.getwellapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {
    EditText startdate, enddate, preName;

    Button addpres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        startdate = (EditText) findViewById(R.id.editTextDate);
        enddate = (EditText) findViewById(R.id.editTextDateEnd);
        preName = (EditText) findViewById(R.id.editTextText);
        addpres = (Button) findViewById(R.id.button);


        addpres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mec = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(mec);
            }
        });

    }
}