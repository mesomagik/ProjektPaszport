package com.example.l03.projektpaszport;

import java.io.Serializable;

/**
 * Created by Bartek on 2016-10-24.
 */
public class WazneInformacje implements Serializable{


    private String przyjmowanie_jedzenia;
    private String przyjmowanie_plynów;
    private String moje_bezpieczenstwo;
    private String korzystanie_z_toalety;
    private String opieka_osobista;
    private String sen;
    private String alergie;

    WazneInformacje(

             String przyjmowanie_jedzenia,
             String przyjmowanie_plynów,
             String moje_bezpieczenstwo,
             String korzystanie_z_toalety,
             String opieka_osobista,
             String sen,
             String alergie
    ){
        this.moje_bezpieczenstwo = moje_bezpieczenstwo;
        this.przyjmowanie_jedzenia = przyjmowanie_jedzenia;
        this.przyjmowanie_plynów = przyjmowanie_plynów;
        this.korzystanie_z_toalety = korzystanie_z_toalety;
        this.opieka_osobista = opieka_osobista;
        this.sen = sen;
        this.alergie = alergie;
    }


    public String getPrzyjmowanie_jedzenia() {
        return przyjmowanie_jedzenia;
    }

    public void setPrzyjmowanie_jedzenia(String przyjmowanie_jedzenia) {
        this.przyjmowanie_jedzenia = przyjmowanie_jedzenia;
    }

    public String getPrzyjmowanie_plynów() {
        return przyjmowanie_plynów;
    }

    public void setPrzyjmowanie_plynów(String przyjmowanie_plynów) {
        this.przyjmowanie_plynów = przyjmowanie_plynów;
    }

    public String getMoje_bezpieczenstwo() {
        return moje_bezpieczenstwo;
    }

    public void setMoje_bezpieczenstwo(String moje_bezpieczenstwo) {
        this.moje_bezpieczenstwo = moje_bezpieczenstwo;
    }

    public String getKorzystanie_z_toalety() {
        return korzystanie_z_toalety;
    }

    public void setKorzystanie_z_toalety(String korzystanie_z_toalety) {
        this.korzystanie_z_toalety = korzystanie_z_toalety;
    }

    public String getOpieka_osobista() {
        return opieka_osobista;
    }

    public void setOpieka_osobista(String opieka_osobista) {
        this.opieka_osobista = opieka_osobista;
    }

    public String getSen() {
        return sen;
    }

    public void setSen(String sen) {
        this.sen = sen;
    }

    public String getAlergie() {
        return alergie;
    }

    public void setAlergie(String alergie) {
        this.alergie = alergie;
    }
    public WazneInformacje returnObj(){
        return this;
    }
}
