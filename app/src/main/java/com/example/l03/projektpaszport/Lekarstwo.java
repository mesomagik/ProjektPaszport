package com.example.l03.projektpaszport;

import java.io.Serializable;
import java.sql.Time;

/**
 * Created by Bartek on 2016-10-24.
 */
public class Lekarstwo implements Serializable{

    private Integer id_lekarstwo;
    private String godzina;
    private String ilosc;
    private String sposob_zazycia;
    private String zdjecie;

    Lekarstwo(
            Integer id_lekarstwo,
            String godzina,
            String ilosc,
            String sposob_zazycia,
            String zdjecie
    ){
        this.id_lekarstwo = id_lekarstwo;
        this.godzina = godzina;
        this. ilosc = ilosc;
        this. sposob_zazycia = sposob_zazycia;
        this. zdjecie = zdjecie;
    }

    public Integer getId_lekarstwo() {
        return id_lekarstwo;
    }

    public void setId_lekarstwo(Integer id_lekarstwo) {
        this.id_lekarstwo = id_lekarstwo;
    }

    public String getGodzina() {
        return godzina;
    }

    public void setGodzina(String godzina) {
        this.godzina = godzina;
    }

    public String getIlosc() {
        return ilosc;
    }

    public void setIlosc(String ilosc) {
        this.ilosc = ilosc;
    }

    public String getSposob_zazycia() {
        return sposob_zazycia;
    }

    public void setSposob_zazycia(String sposob_zazycia) {
        this.sposob_zazycia = sposob_zazycia;
    }

    public String getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }
    public Lekarstwo returnObj(){
        return this;
    }

}
