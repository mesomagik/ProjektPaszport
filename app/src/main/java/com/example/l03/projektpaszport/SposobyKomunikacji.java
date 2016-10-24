package com.example.l03.projektpaszport;

import java.io.Serializable;

/**
 * Created by Bartek on 2016-10-24.
 */
public class SposobyKomunikacji implements Serializable{


    private String informacje;
    private String jezyki;
    private String charakterystyczne_zachowania;
    private String wyrazanie_checi_niecheci;
    private String przekazywanie_stanow_emocjonalnych;

    SposobyKomunikacji(
             String informacje,
             String jezyki,
             String charakterystyczne_zachowania,
             String wyrazanie_checi_niecheci,
             String przekazywanie_stanow_emocjonalnych
    ){
        this.charakterystyczne_zachowania = charakterystyczne_zachowania;
        this.informacje = informacje;
        this.jezyki = jezyki;
        this.przekazywanie_stanow_emocjonalnych = przekazywanie_stanow_emocjonalnych;
        this.wyrazanie_checi_niecheci = wyrazanie_checi_niecheci;
    }

    public String getInformacje() {
        return informacje;
    }

    public void setInformacje(String informacje) {
        this.informacje = informacje;
    }

    public String getJezyki() {
        return jezyki;
    }

    public void setJezyki(String jezyki) {
        this.jezyki = jezyki;
    }

    public String getCharakterystyczne_zachowania() {
        return charakterystyczne_zachowania;
    }

    public void setCharakterystyczne_zachowania(String charakterystyczne_zachowania) {
        this.charakterystyczne_zachowania = charakterystyczne_zachowania;
    }

    public String getWyrazanie_checi_niecheci() {
        return wyrazanie_checi_niecheci;
    }

    public void setWyrazanie_checi_niecheci(String wyrazanie_checi_niecheci) {
        this.wyrazanie_checi_niecheci = wyrazanie_checi_niecheci;
    }

    public String getPrzekazywanie_stanow_emocjonalnych() {
        return przekazywanie_stanow_emocjonalnych;
    }

    public void setPrzekazywanie_stanow_emocjonalnych(String przekazywanie_stanow_emocjonalnych) {
        this.przekazywanie_stanow_emocjonalnych = przekazywanie_stanow_emocjonalnych;
    }

    public SposobyKomunikacji returnObj(){
        return this;
    }
}