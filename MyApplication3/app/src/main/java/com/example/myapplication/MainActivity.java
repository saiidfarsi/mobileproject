package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginbutn, registration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username= findViewById(R.id.usrnm);
        password= findViewById(R.id.pass);
        loginbutn= findViewById(R.id.login);
        registration= findViewById(R.id.register);

        loginbutn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mec=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(mec);
            }
        });



    }
}