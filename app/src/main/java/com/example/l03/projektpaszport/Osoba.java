package com.example.l03.projektpaszport;

/**
 * Created by Bartek on 2016-10-21.
 */
public class Osoba {

    private Integer id_osoba;
    private String Imie_nazwisko;
    private String telefon;
    private String email;
    private String adres;
    private Integer relacja;
    private Boolean domownik;

    Osoba(Integer id_osoba,
          String Imie_nazwisko,
          String telefon,
          String email,
          String adres,
          Integer relacja,
          Boolean domownik){

        this.id_osoba = id_osoba;
        this.Imie_nazwisko = Imie_nazwisko;
        this.telefon = telefon;
        this.email = email;
    }


}
