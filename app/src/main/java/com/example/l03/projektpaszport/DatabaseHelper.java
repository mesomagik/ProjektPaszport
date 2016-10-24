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
        super(context, "baza", null,3 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Osoba (id_osoba INTEGER PRIMARY KEY, imie_nazwisko TEXT, zdjecie TEXT, informacje TEXT, data_ur TEXT, kontakt TEXT, relacja TEXT);");
        db.execSQL("CREATE TABLE Choroba (id_choroby INTEGER PRIMARY KEY, nazwa TEXT, opis TEXT,pierwsza_pomoc TEXT,link_youtube TEXT);");
        db.execSQL("CREATE TABLE Lekarstwo (id_lekarstwo INTEGER PRIMARY KEY, godzina TEXT, ilosc TEXT,sposob_zazycia TEXT,zdjecie TEXT);");
        db.execSQL("CREATE TABLE Preferencje (id_preferencja INTEGER PRIMARY KEY, lubie BOOLEAN, zdjecie TEXT,opis TEXT);");
        db.execSQL("CREATE TABLE SposobyKomunikacj (informacje TEXT, jezyki TEXT, charakterystyczne_zachowania TEXT, wyrazanie_checi_niecheci TEXT, przekazywanie_stanow_emocjonalnych TEXT);");
        db.execSQL("CREATE TABLE WazneInformacje (moje_zmysly TEXT, przyjmowanie_jedzenia TEXT, przyjmowanie_plynów TEXT, moje_bezpieczenstwo TEXT, korzystanie_z_toalety TEXT, opieka_osobista TEXT, sen TEXT, alergie TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Osoba");
        db.execSQL("DROP TABLE IF EXISTS Choroba");
        db.execSQL("DROP TABLE IF EXISTS Lekarstwo");
        db.execSQL("DROP TABLE IF EXISTS Preferencje");
        db.execSQL("DROP TABLE IF EXISTS SposobyKomunikacj");
        db.execSQL("DROP TABLE IF EXISTS WazneInformacje");

        onCreate(db);

    }

    public long createOsoba( Integer id_osoba,String imie_nazwisko,String zdjecie,String informacje,String data_ur,String kontakt,String relacja) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("imie_nazwisko", imie_nazwisko);
        values.put("zdjecie", zdjecie);
        values.put("informacje", informacje);
        values.put("data_ur", data_ur);
        values.put("kontakt", kontakt);
        values.put("relacja", relacja);

        long osoba_id = db.insert("Osoba", null, values); //zwraca id
        return osoba_id;

    }



    public int updateOsoba(Osoba osoba) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("imie_nazwisko", osoba.getImie_nazwisko());
        values.put("zdjecie", osoba.getZdjecie());
        values.put("informacje", osoba.getInformacje());
        values.put("data_ur", osoba.getData_ur());
        values.put("kontakt", osoba.getKontakt());
        values.put("relacja", osoba.getRelacja());

        Integer wynik = db.update("Osoba", values, "id=" + osoba.getId_osoba().toString(), null);
        Log.e("wynik edycji",wynik.toString());
        return wynik;

    }

    public void deleteOsoba(Osoba osoba) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Osoba","id="+osoba.getId_osoba().toString(),null );

    }



}