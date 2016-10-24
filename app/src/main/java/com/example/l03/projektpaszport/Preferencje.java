package com.example.l03.projektpaszport;

import java.io.Serializable;

/**
 * Created by Bartek on 2016-10-24.
 */
public class Preferencje implements Serializable{

    private Integer id_preferencja;
    private Boolean lubie;
    private String zdjecie;
    private String opis;

    Preferencje(
             Integer id_preferencja,
             Boolean lubie,
             String zdjecie,
             String opis
    ){
        this.id_preferencja = id_preferencja;
        this.lubie = lubie;
        this.zdjecie = zdjecie;
        this.opis = opis;

    }

    public Integer getId_preferencja() {
        return id_preferencja;
    }

    public void setId_preferencja(Integer id_preferencja) {
        this.id_preferencja = id_preferencja;
    }

    public Boolean getLubie() {
        return lubie;
    }

    public void setLubie(Boolean lubie) {
        this.lubie = lubie;
    }

    public String getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Preferencje returnObj(){
        return this;
    }
}
