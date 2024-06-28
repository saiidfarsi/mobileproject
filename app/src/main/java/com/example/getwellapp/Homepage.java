package com.example.getwellapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Homepage extends AppCompatActivity {

    //private String prescriptions[];
    //public String prescriptionHolder;
    BottomNavigationView nav;
    private ListView medicineListView;
    dbHelper databaseHelper;
    ListView listView;
    ArrayList<String> prescriptionList;
    ArrayAdapter<String> adapter;

    //private List<medicin> medicineList;
    //private MedicinAdapter medicineAdapter;




    //ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_homepage);

        nav=findViewById(R.id.bottom_navigation);

        databaseHelper = new dbHelper(this);
        listView = findViewById(R.id.medicineListView);
        prescriptionList = new ArrayList<>();

        loadPrescriptions();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, prescriptionList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Homepage.this, MainActivity3.class);
                intent.putExtra("prescriptionId", i + 1);
                startActivity(intent);
            }
        });



        nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        Intent home = new Intent(Homepage.this, Homepage.class);
                        startActivity(home);
                        break;

                    case R.id.prescription:
                        Intent pre = new Intent(Homepage.this, MainActivity2.class);
                        startActivity(pre);
                        break;

                    case R.id.action_add:
                        Intent add = new Intent(Homepage.this, MainActivity2.class);
                        startActivity(add);
                        break;

                    case R.id.action_account:
                        Intent acc = new Intent(Homepage.this, Homepage.class);
                        startActivity(acc);
                        break;

                    case R.id.action_settings:
                        Intent sett = new Intent(Homepage.this, Homepage.class);
                        startActivity(sett);
                        break;
                }
                return true;
        }
        });



    }

    private void loadPrescriptions() {
        Cursor cursor = databaseHelper.getAllPrescriptions();
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                prescriptionList.add(name);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }
}