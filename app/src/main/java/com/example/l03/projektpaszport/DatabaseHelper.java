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
        super(context, "baza", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Osoba (id_osoba INTEGER PRIMARY KEY, imie_nazwisko TEXT, zdjecie TEXT, informacje TEXT, data_ur TEXT, kontakt TEXT, relacja TEXT);");
        db.execSQL("CREATE TABLE Choroba (id_choroby INTEGER PRIMARY KEY, nazwa TEXT, opis TEXT,pierwsza_pomoc TEXT,link_youtube TEXT);");
        db.execSQL("CREATE TABLE Lekarstwo (id_lekarstwo INTEGER PRIMARY KEY, godzina TEXT, ilosc TEXT,sposob_zazycia TEXT,zdjecie TEXT);");
        db.execSQL("CREATE TABLE Preferencja (id_preferencja INTEGER PRIMARY KEY, lubie BOOLEAN, zdjecie TEXT,opis TEXT);");
        db.execSQL("CREATE TABLE SposobyKomunikacji (moje_zmysly TEXT, charakterystyczne_zachowania TEXT, przekazywanie_emocji TEXT);");
        db.execSQL("CREATE TABLE Video (id_video INTEGER PRIMARY KEY, url TEXT, opis TEXT)");
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
        db.execSQL("DROP TABLE IF EXISTS video");

        onCreate(db);

    }

    public long createVideo( String url, String opis){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("url",url);
        values.put("opis",opis);

        long id_video = db.insert("Video",null,values);
        return id_video;
    }

    public long updateVideo(MojeVideo video){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("url",video.getUrl());
        values.put("opis",video.getOpis());

        Integer wynik = db.update("Video",values,"id_video='" + video.getId_video().toString() +"'",null);
        return wynik;
    }

    public void deleteVideo(MojeVideo video){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Video","id_video='" + video.getId_video().toString() +"'",null);
    }

    public List<MojeVideo> getAllMojeVideo(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<MojeVideo> listVideo = new ArrayList<>();

        String query = "select * from Video";
        Cursor c = db.rawQuery(query,null);

        if(c.moveToFirst()){
            do{
                MojeVideo vid = new MojeVideo(
                        c.getInt(c.getColumnIndex("id_video")),
                        c.getString(c.getColumnIndex("url")),
                        c.getString(c.getColumnIndex("opis"))
                );
                listVideo.add(vid);
            }while(c.moveToNext());
            c.moveToFirst();
        }
        return listVideo;
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

    /********************
     * Moje Zdrowie
     ****************************/

    public long createLekarstwo(String godzina, String ilosc, String sposob_zazycia, String zdjecie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("godzina", godzina);
        values.put("ilosc", ilosc);
        values.put("sposob_zazycia", sposob_zazycia);
        values.put("zdjecie", zdjecie);


        long lekarstwo_id = db.insert("Lekarstwo", null, values); //zwraca id
        return lekarstwo_id;
    }

    public int updateLekarstwo(Lekarstwo lekarstwo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("godzina", lekarstwo.getGodzina());
        values.put("ilosc", lekarstwo.getIlosc());
        values.put("sposob_zazycia", lekarstwo.getSposob_zazycia());
        values.put("zdjecie", lekarstwo.getZdjecie());

        Integer wynik = db.update("Lekarstwo", values, "id_lekarstwo='" + lekarstwo.getId_lekarstwo().toString() + "'", null);
        Log.e("wynik edycji", wynik.toString());
        return wynik;

    }

    public void deleteLekarstwo(Lekarstwo lekarstwo) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Lekarstwo", "id_lekarstwo=" + lekarstwo.getId_lekarstwo().toString(), null);

    }

    public List<Lekarstwo> getAllLekarstwo() {
        List<Lekarstwo> lekarstwa = new ArrayList<>();
        String query = "select * from Lekarstwo";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Lekarstwo u = new Lekarstwo(
                        c.getInt(c.getColumnIndex("id_lekarstwo")),
                        c.getString(c.getColumnIndex("godzina")),
                        c.getString(c.getColumnIndex("ilosc")),
                        c.getString(c.getColumnIndex("sposob_zazycia")),
                        c.getString(c.getColumnIndex("zdjecie")));
                lekarstwa.add(u);
            } while (c.moveToNext());
            c.moveToFirst();
        }
        return lekarstwa;
    }

//Preferencja
    //CREATE TABLE Preferencja (id_preferencja INTEGER PRIMARY KEY, lubie BOOLEAN, zdjecie TEXT,opis TEXT);

    public long createPreferencja(Boolean lubie, String zdjecie, String opis) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("lubie", lubie);
        values.put("zdjecie", zdjecie);
        values.put("opis", opis);

        long preferencja_id = db.insert("Preferencja", null, values); //zwraca id

        return preferencja_id;

    }


    public int updatePreferencja(Preferencja preferencja) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_preferencja", preferencja.getId_preferencja());
        values.put("lubie", preferencja.getLubie());
        values.put("zdjecie", preferencja.getZdjecie());
        values.put("opis", preferencja.getOpis());

        Integer wynik = db.update("Preferencja", values, "id_preferencja='" + preferencja.getId_preferencja().toString()+"'", null);
        Log.e("wynik edycji", wynik.toString());
        return wynik;

    }

    public void deletePreferencja(Preferencja preferencja) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("Preferencja", "id_preferencja='" + preferencja.getId_preferencja().toString()+"'", null);

    }

    public List<Preferencja> getAllPreferencjaByLubie(Boolean lubie) {
        List<Preferencja> Preferencje = new ArrayList<>();

        String  booleanToInt;
        if(lubie)
            booleanToInt = "1";
        else
            booleanToInt = "0";

        String query = "select * from Preferencja where lubie='" + booleanToInt+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Preferencje.add(
                        new Preferencja(c.getInt(c.getColumnIndex("id_preferencja")),
                                c.getInt(c.getColumnIndex("lubie")) > 0,
                                c.getString(c.getColumnIndex("zdjecie")),
                                c.getString(c.getColumnIndex("opis"))
                        )
                );
            } while (c.moveToNext());
            c.moveToFirst();
        }
        return Preferencje;
    }

    public List<Preferencja> getAllPreferencja() {
        List<Preferencja> Preferencje = new ArrayList<>();

        String query = "select * from Preferencja";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Preferencje.add(
                        new Preferencja(c.getInt(c.getColumnIndex("id_preferencja")),
                                c.getInt(c.getColumnIndex("lubie")) > 0,
                                c.getString(c.getColumnIndex("zdjecie")),
                                c.getString(c.getColumnIndex("opis"))
                        )
                );
            } while (c.moveToNext());
            c.moveToFirst();
        }
        return Preferencje;
    }
}