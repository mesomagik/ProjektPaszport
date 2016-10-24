package com.example.l03.projektpaszport;

import java.io.Serializable;

/**
 * Created by Bartek on 2016-10-24.
 */
public class Choroba implements Serializable{

    private Integer id_choroby;
    private String nazwa;
    private String opis;
    private String pierwsza_pomoc;
    private String link_youtube;

    Choroba(
             Integer id_choroby,
             String nazwa,
             String opis,
             String pierwsza_pomoc,
             String link_youtube
    ){
        this.id_choroby = id_choroby;
        this.nazwa = nazwa;
        this.opis = opis;
        this.pierwsza_pomoc = pierwsza_pomoc;
        this.link_youtube = link_youtube;
    }

    public Integer getId_choroby() {
        return id_choroby;
    }

    public void setId_choroby(Integer id_choroby) {
        this.id_choroby = id_choroby;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getPierwsza_pomoc() {
        return pierwsza_pomoc;
    }

    public void setPierwsza_pomoc(String pierwsza_pomoc) {
        this.pierwsza_pomoc = pierwsza_pomoc;
    }

    public String getLink_youtube() {
        return link_youtube;
    }

    public void setLink_youtube(String link_youtube) {
        this.link_youtube = link_youtube;
    }

    public Choroba returnObj(){
        return this;
    }
}
