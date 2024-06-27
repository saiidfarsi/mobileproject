package com.example.getwellapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button signUpButton, loginButton;
    EditText username, pass;
    String UsernameHolder, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    dbHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;
    public static final String UserEmail = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Initialize views
        signUpButton = findViewById(R.id.sign_up_button);
        username = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        sqLiteHelper = new dbHelper(this);


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, register2.class);

                startActivity(intent);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Calling EditText is empty or no method.
                CheckEditTextStatus();
                // Calling login method.
                LoginFunction();
            }
        });
    }

    @SuppressLint("Range")
    public void LoginFunction(){
        if(EditTextEmptyHolder) {
            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(dbHelper.TABLE_NAME, null, " " +
                    dbHelper.TC_mail + "=?", new String[]{UsernameHolder}, null, null, null);
            while (cursor.moveToNext()) {
                if (cursor.isFirst()) {
                    cursor.moveToFirst();
// Storing Password associated with entered email.
                    TempPassword =
                            cursor.getString(cursor.getColumnIndex(dbHelper.TC_pass));
                    // Closing cursor.
                    cursor.close();
                }
            }
            // Calling method to check final result ..
            CheckFinalResult();
        }
        else {
            //If any of login EditText empty then this block will be
            //executed.
                    Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();
        }
    }

    // Checking EditText is empty or not.
    public void CheckEditTextStatus(){
        // Getting value from All EditText and storing into String Variables.
        UsernameHolder = username.getText().toString();
        PasswordHolder = pass.getText().toString();
        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(UsernameHolder) ||
                TextUtils.isEmpty(PasswordHolder)){
            EditTextEmptyHolder = false ;
        }
        else {
            EditTextEmptyHolder = true ;
        }
    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){
        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {
            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
                    // Going to Dashboard activity after login success message.
                    Intent intent = new Intent(MainActivity.this, Homepage.class);
            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserEmail, UsernameHolder);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this,"UserName or Password is Wrong,Please Try Again.",Toast.LENGTH_LONG).show();
        }
        TempPassword = "NOT_FOUND" ;
    }



}
