package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
     Button btn,loginb;
     EditText usrnm, pass, repass, mail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn=(Button)findViewById(R.id.loginbtn);
        usrnm=(EditText) findViewById(R.id.usernamenew);
        pass=(EditText) findViewById(R.id.passnew);
        repass=(EditText) findViewById(R.id.repassnew);
        mail=(EditText) findViewById(R.id.email);
        loginb=(Button) findViewById(R.id.registerbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mec=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(mec);
            }
        });
    }
}