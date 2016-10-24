package com.example.l03.projektpaszport;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Bartek on 2016-10-21.
 */
public class Osoba implements Serializable{

    private Integer id_osoba;
    private String imie_nazwisko;
    private String zdjecie;
    private String informacje;
    private String data_ur;
    private String kontakt;
    private String relacja;


    Osoba(  Integer id_osoba,
            String imie_nazwisko,
            String zdjecie,
            String informacje,
            String data_ur,
            String kontakt,
            String relacja){

        this.id_osoba = id_osoba;
        this.imie_nazwisko = imie_nazwisko;
        this.zdjecie = zdjecie;
        this.informacje = informacje;
        this.data_ur = data_ur;
        this.kontakt = kontakt;
        this.relacja = relacja;

    }


    public Integer getId_osoba() {
        return id_osoba;
    }

    public void setId_osoba(Integer id_osoba) {
        this.id_osoba = id_osoba;
    }

    public String getImie_nazwisko() {
        return imie_nazwisko;
    }

    public void setImie_nazwisko(String imie_nazwisko) {
        this.imie_nazwisko = imie_nazwisko;
    }

    public String getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }

    public String getInformacje() {
        return informacje;
    }

    public void setInformacje(String informacje) {
        this.informacje = informacje;
    }

    public String getData_ur() {
        return data_ur;
    }

    public void setData_ur(String data_ur) {
        this.data_ur = data_ur;
    }

    public String getKontakt() {
        return kontakt;
    }

    public void setKontakt(String kontakt) {
        this.kontakt = kontakt;
    }

    public String getRelacja() {
        return relacja;
    }

    public void setRelacja(String relacja) {
        this.relacja = relacja;
    }

    public Osoba returnObj(){
        return this;
    }
}
