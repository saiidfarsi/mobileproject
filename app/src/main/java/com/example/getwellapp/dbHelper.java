package com.example.getwellapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME = "getWell";
    public static final String TABLE_NAME = "users";
    public static final String TABLE_pres = "prescriptions";
    public static final String TABLE_medicines = "meds";
    public static final String TC_id = "id";
    public static final String pres_id = "idpres";
    public static final String med_id = "idmed";
    public static final String TC_name = "name";
    public static final String TC_mail = "email";
    public static final String TC_pass = "password";
    public static final String TC_medName = "medName";
    public static final String TC_medAmount = "medAmount";
    public static final String TC_medRepeatition = "medRep";
    private static final String COLUMN_PRESCRIPTION_ID_FK = "prescription_id";
    public static final String TC_medPer = "medPer";
    public static final String TC_presName = "preName";
    public static final String TC_presSdate = "presSdate";
    public static final String TC_presEdate = "presEdate";

    dbHelper(Context ct){
        super(ct,"DATABASE_NAME",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String userTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + TC_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TC_name + " VARCHAR, " + TC_mail + " VARCHAR, " + TC_pass + " VARCHAR)";
        db.execSQL(userTable);

        String presTable = "CREATE TABLE IF NOT EXISTS " + TABLE_pres + "(" + pres_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TC_presName + " VARCHAR, " + TC_presSdate + " TEXT, " + TC_presEdate + " TEXT)";
        db.execSQL(presTable);

        String medTable = "CREATE TABLE IF NOT EXISTS " + TABLE_medicines + "(" + med_id + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_PRESCRIPTION_ID_FK + " INTEGER, " + TC_medName + " VARCHAR, " + TC_medAmount + " NUMBER, " + TC_medRepeatition + " NUMBER ," + TC_medPer +" VARCHAR)";
        db.execSQL(medTable);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);

        String dropTablepre = "DROP TABLE IF EXISTS " + TABLE_pres;
        db.execSQL(dropTablepre);
        onCreate(db);

        String dropTablemed = "DROP TABLE IF EXISTS " + TABLE_medicines;
        db.execSQL(dropTablemed);
        onCreate(db);
    }

    // Methods to insert data into the tables
    public boolean addPrescription(String name, String startDate, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TC_presName, name);
        contentValues.put(TC_presSdate, startDate);
        contentValues.put(TC_presEdate, endDate);
        long result = db.insert(TABLE_pres, null, contentValues);
        return result != -1;
    }

    public boolean addMedicine(int prescriptionId, String name, String dose, String frequency) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRESCRIPTION_ID_FK, prescriptionId);
        contentValues.put(TC_medName, name);
        contentValues.put(TC_medAmount, dose);
        contentValues.put(TC_medRepeatition, frequency);
        contentValues.put(TC_medPer, frequency);
        long result = db.insert(TABLE_medicines, null, contentValues);
        return result != -1;
    }

    // Method to get all prescriptions
    public List<listViewpress> getAllPrescriptions() {
        List<listViewpress> prescriptions = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM prescriptions", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String startDate = cursor.getString(2);
                String endDate = cursor.getString(3);
                prescriptions.add(new listViewpress(id, name, startDate, endDate));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return prescriptions;
    }

    // Method to get medicines for a specific prescription
    public Cursor getMedicinesForPrescription(int prescriptionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_medicines + " WHERE " + COLUMN_PRESCRIPTION_ID_FK + " = " + prescriptionId, null);
    }


}
