package com.example.l03.projektpaszport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Bartek on 2016-10-18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "baza", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE lekarz (imie TEXT, nazwisko TEXT);");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS lekarz");

        onCreate(db);

    }

    public long createLekarz(String imie,String nazwisko) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nazwisko", nazwisko);
        values.put("imie", imie);

        long lekarz_id = db.insert("lekarz", null, values); //zwraca id
        return lekarz_id;

    }



    public int updateLekarz(String imie,String nazwisko) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nazwisko", nazwisko);
        values.put("imie", imie);


        Integer wynik = db.update("lekarz", values, "id=0", null);
        Log.e("wynik edycji", wynik.toString());
        return wynik;

    }

    public ArrayList<String> getLekarz(){
        String query = "select * from lekarz ;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);
        ArrayList<String> lekarz = new ArrayList<>();

        if(c.moveToFirst()) {
            do {
                lekarz.add(c.getString((c.getColumnIndex("nazwisko"))));
                lekarz.add(c.getString((c.getColumnIndex("imie"))));
            } while (c.moveToNext());

        }
        return lekarz;
    }

}