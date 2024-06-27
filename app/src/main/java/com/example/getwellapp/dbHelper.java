package com.example.getwellapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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

        String presTable = "CREATE TABLE IF NOT EXISTS " + TABLE_pres + "(" + pres_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TC_presName + " VARCHAR, " + TC_presSdate + " DATE, " + TC_presEdate + " DATE)";
        db.execSQL(presTable);

        String medTable = "CREATE TABLE IF NOT EXISTS " + TABLE_medicines + "(" + med_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TC_medName + " VARCHAR, " + TC_medAmount + " NUMBER, " + TC_medRepeatition + " NUMBER ," + TC_medPer +" VARCHAR)";
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


}
