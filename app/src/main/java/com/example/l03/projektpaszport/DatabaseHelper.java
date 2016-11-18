package com.example.l03.projektpaszport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartek on 2016-10-18.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, "baza", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Osoba (id_osoba INTEGER PRIMARY KEY, imie_nazwisko TEXT, zdjecie TEXT, informacje TEXT, data_ur TEXT, kontakt TEXT, relacja TEXT);");
        db.execSQL("CREATE TABLE Choroba (id_choroby INTEGER PRIMARY KEY, nazwa TEXT, opis TEXT,pierwsza_pomoc TEXT,link_youtube TEXT);");
        db.execSQL("CREATE TABLE Lekarstwo (id_lekarstwo INTEGER PRIMARY KEY, godzina TEXT, ilosc TEXT,sposob_zazycia TEXT,zdjecie TEXT);");
        db.execSQL("CREATE TABLE Preferencja (id_preferencja INTEGER PRIMARY KEY, lubie BOOLEAN, zdjecie TEXT,opis TEXT);");
        db.execSQL("CREATE TABLE SposobyKomunikacji (moje_zmysly TEXT, charakterystyczne_zachowania TEXT, przekazywanie_emocji TEXT);");
        db.execSQL("CREATE TABLE WazneInformacje (przyjmowanie_jedzenia TEXT, przyjmowanie_plynów TEXT, moje_bezpieczenstwo TEXT, korzystanie_z_toalety TEXT, opieka_osobista TEXT, sen TEXT, alergie TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Osoba");
        db.execSQL("DROP TABLE IF EXISTS Choroba");
        db.execSQL("DROP TABLE IF EXISTS Lekarstwo");
        db.execSQL("DROP TABLE IF EXISTS Preferencja");
        db.execSQL("DROP TABLE IF EXISTS SposobyKomunikacji");
        db.execSQL("DROP TABLE IF EXISTS WazneInformacje");

        onCreate(db);

    }

    public long createOsoba(String imie_nazwisko, String zdjecie, String informacje, String data_ur, String kontakt, String relacja) {
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

        Integer wynik = db.update("Osoba", values, "id_osoba='" + osoba.getId_osoba().toString() + "'", null);
        Log.e("wynik edycji", wynik.toString());
        return wynik;

    }

    public void deleteOsoba(Osoba osoba) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Osoba", "id_osoba=" + osoba.getId_osoba().toString(), null);

    }

    public List<Osoba> getAllOsoba() {
        List<Osoba> osoby = new ArrayList<>();
        String query = "select * from Osoba";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Osoba u = new Osoba(
                        c.getInt(c.getColumnIndex("id_osoba")),
                        c.getString(c.getColumnIndex("imie_nazwisko")),
                        c.getString(c.getColumnIndex("zdjecie")),
                        c.getString(c.getColumnIndex("informacje")),
                        c.getString(c.getColumnIndex("data_ur")),
                        c.getString(c.getColumnIndex("kontakt")),
                        c.getString(c.getColumnIndex("relacja")));
                osoby.add(u);
            } while (c.moveToNext());
            c.moveToFirst();
        }
        return osoby;
    }

    public Boolean checkPacjentDatabase() {
        String query = "select * from Osoba where relacja='pacjent'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                return true;
            } while (c.moveToNext());
        }
        return false;
    }

    public List<Osoba> getAllOsobaByRelacja(String relacja) {
        List<Osoba> osoby = new ArrayList<>();
        String query = "select * from Osoba where relacja='" + relacja + "'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Osoba u = new Osoba(
                        c.getInt(c.getColumnIndex("id_osoba")),
                        c.getString(c.getColumnIndex("imie_nazwisko")),
                        c.getString(c.getColumnIndex("zdjecie")),
                        c.getString(c.getColumnIndex("informacje")),
                        c.getString(c.getColumnIndex("data_ur")),
                        c.getString(c.getColumnIndex("kontakt")),
                        c.getString(c.getColumnIndex("relacja")));
                osoby.add(u);
            } while (c.moveToNext());
            c.moveToFirst();
        }
        return osoby;
    }

    public SposobyKomunikacji getSposobyKomunikacji() {
        SposobyKomunikacji sposobyKomunikacji = null;
        String query = "select * from SposobyKomunikacji";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                sposobyKomunikacji = new SposobyKomunikacji(
                        c.getString(c.getColumnIndex("moje_zmysly")),
                        c.getString(c.getColumnIndex("charakterystyczne_zachowania")),
                        c.getString(c.getColumnIndex("przekazywanie_emocji")));
            } while (c.moveToNext());
            c.moveToFirst();
        }
        return sposobyKomunikacji;
    }

    public long createSposobyKomunikacji(String moje_zmysly, String przekazywanie_emocji, String charakterystyczne_zachowania) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("moje_zmysly", moje_zmysly);
        values.put("przekazywanie_emocji", przekazywanie_emocji);
        values.put("charakterystyczne_zachowania", charakterystyczne_zachowania);


        long id = db.insert("SposobyKomunikacji", null, values); //zwraca id
        return id;
    }

    public int updateSposobyKomunikacji(SposobyKomunikacji sposobyKomunikacji) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("moje_zmysly", sposobyKomunikacji.getMoje_zmysly());
        values.put("charakterystyczne_zachowania", sposobyKomunikacji.getCharakterystyczne_zachowania());
        values.put("przekazywanie_emocji", sposobyKomunikacji.getPrzekazywanie_emocji());

        Integer wynik = db.update("SposobyKomunikacji", values, "", null);
        Log.e("wynik edycji", wynik.toString());
        return wynik;
    }

    public Boolean checkSposobyKomunikacjiDatabase() {
        String query = "select * from SposobyKomunikacji";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                return true;
            } while (c.moveToNext());
        }
        return false;
    }

    /********************
     * Ważne Informacje
     ****************************/

    public Boolean checkWazneInformacjeDatabase() {
        String query = "select * from WazneInformacje";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                return true;
            } while (c.moveToNext());
        }
        return false;
    }

    public WazneInformacje getWazneInformacje() {

        WazneInformacje wazneInformacje = null;
        String query = "select * from WazneInformacje";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                wazneInformacje = new WazneInformacje(
                        c.getString(c.getColumnIndex("przyjmowanie_jedzenia")),
                        c.getString(c.getColumnIndex("przyjmowanie_plynów")),
                        c.getString(c.getColumnIndex("moje_bezpieczenstwo")),
                        c.getString(c.getColumnIndex("korzystanie_z_toalety")),
                        c.getString(c.getColumnIndex("opieka_osobista")),
                        c.getString(c.getColumnIndex("sen")),
                        c.getString(c.getColumnIndex("alergie")));
            } while (c.moveToNext());
            c.moveToFirst();
        }

        return wazneInformacje;
    }


    public long createWazneInformacje(String przyjmowanie_jedzenia, String przyjmowanie_plynów, String moje_bezpieczenstwo, String korzystanie_z_toalety, String opieka_osobista, String sen, String alergie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("przyjmowanie_jedzenia", przyjmowanie_jedzenia);
        values.put("przyjmowanie_plynów", przyjmowanie_plynów);
        values.put("moje_bezpieczenstwo", moje_bezpieczenstwo);
        values.put("korzystanie_z_toalety", korzystanie_z_toalety);
        values.put("opieka_osobista", opieka_osobista);
        values.put("sen", sen);
        values.put("alergie", alergie);

        long id = db.insert("WazneInformacje", null, values); //zwraca id
        return id;
    }

    public int updateWazneInformacje(WazneInformacje wazneInformacje) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("przyjmowanie_jedzenia", wazneInformacje.getPrzyjmowanie_jedzenia());
        values.put("przyjmowanie_plynów", wazneInformacje.getPrzyjmowanie_plynów());
        values.put("moje_bezpieczenstwo", wazneInformacje.getMoje_bezpieczenstwo());
        values.put("korzystanie_z_toalety", wazneInformacje.getKorzystanie_z_toalety());
        values.put("opieka_osobista", wazneInformacje.getOpieka_osobista());
        values.put("sen", wazneInformacje.getSen());
        values.put("alergie", wazneInformacje.getAlergie());

        Integer wynik = db.update("WazneInformacje", values, "", null);
        Log.e("wynik edycji", wynik.toString());
        return wynik;
    }


}