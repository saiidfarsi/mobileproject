package com.example.getwellapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Homepage extends AppCompatActivity {


    BottomNavigationView nav;
    dbHelper databaseHelper;
    RecyclerView recyclerView;
    presAdapter adapter;
    ListView listView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_homepage);

        nav=findViewById(R.id.bottom_navigation);

        databaseHelper = new dbHelper(this);
        recyclerView = findViewById(R.id.medicineListView);


        List<listViewpress> prescriptionList = databaseHelper.getAllPrescriptions();

        adapter = new presAdapter(prescriptionList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


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

    protected void onResume() {
        super.onResume();
        List<listViewpress> prescriptionList = databaseHelper.getAllPrescriptions();
        adapter = new presAdapter(prescriptionList, this);
        recyclerView.setAdapter(adapter);
    }

}