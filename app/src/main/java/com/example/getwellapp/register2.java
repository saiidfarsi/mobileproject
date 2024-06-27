package com.example.getwellapp;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register2 extends AppCompatActivity {
    Button login, signup;
    EditText name, email, password;

    String passHolder, SQLiteDataBaseQueryHolder, nameholder, emailholder;

    SQLiteDatabase sqLiteDatabaseObj;
    Boolean EditTextEmptyHolder;
    dbHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";



    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register2);

        signup = (Button) findViewById(R.id.signup2);
        name = (EditText) findViewById(R.id.fname);
        email = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.nPass);
        login = (Button) findViewById(R.id.log_in_button);
        sqLiteHelper = new dbHelper(this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(register2.this, MainActivity.class);

                startActivity(intent);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Checking EditText is empty or Not.
                CheckEditTextStatus();
                // Method to check Email is already exists or not.
                CheckingEmailAlreadyExistsOrNot();

                SQLiteDataBaseBuild();
                // Creating SQLite table if dose n't exists.
                SQLiteTableBuild();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();

            }

        });

    }

    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(dbHelper.DATABASE_NAME,
                Context.MODE_PRIVATE, null);
    }

    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + dbHelper.TABLE_NAME + "(" + dbHelper.TC_id + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + dbHelper.TC_name + " VARCHAR, " + dbHelper.TC_mail + " VARCHAR, " + dbHelper.TC_pass + " VARCHAR);");

        }
    public void InsertDataIntoSQLiteDatabase() {
        // If editText is not empty then this block will executed.
        if (EditTextEmptyHolder == true) {
            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO " + dbHelper.TABLE_NAME + " (name,email,password) VALUES('" + nameholder + "', '" + emailholder + "', '" + passHolder + "');";
            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            // Closing SQLite database object.
            sqLiteDatabaseObj.close();
            // Printing toast message after done inserting.
            Toast.makeText(register2.this, "User Registered Successfully", Toast.LENGTH_LONG).show();
        }
        // This block will execute if any of the registration EditText is empty.
        else {
            // Printing toast message if any of EditText is empty.
            Toast.makeText(register2.this, "Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }
    }
    public void EmptyEditTextAfterDataInsert(){
        name.getText().clear();
        email.getText().clear();
        password.getText().clear();
    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){
        // Getting value from All EditText and storing into String Variables.
        nameholder = name.getText().toString() ;
        emailholder = email.getText().toString();
        passHolder = password.getText().toString();
        if(TextUtils.isEmpty(nameholder) || TextUtils.isEmpty(emailholder) ||
                TextUtils.isEmpty(passHolder)){
            EditTextEmptyHolder = false ;
        }
        else {
            EditTextEmptyHolder = true ;
        }
    }

    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(dbHelper.TABLE_NAME, null, " " + dbHelper.TC_mail + "=?", new String[]{emailholder}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";
                // Closing cursor.
                cursor.close();
            }
            else {
                    Intent intent = new Intent(register2.this, Homepage.class);
                }

        }
        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();
    }


// Checking result
public void CheckFinalResult(){
    // Checking whether email is already exists or not.
    if(F_Result.equalsIgnoreCase("Email Found"))
    {
        // If email is exists then toast msg will display.
        Toast.makeText(register2.this,"Email Already Exists",Toast.LENGTH_LONG).show();
    }
    else {
        // If email already dose n't exists then user registration details will entered to SQLite database.
        InsertDataIntoSQLiteDatabase();
    }
    F_Result = "Not_Found" ;
}
}




