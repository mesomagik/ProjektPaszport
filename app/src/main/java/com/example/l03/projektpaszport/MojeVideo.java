package com.example.l03.projektpaszport;

import java.io.Serializable;

/**
 * Created by Bartek on 2016-12-06.
 */
public class MojeVideo implements Serializable {

    private Integer id_video;
    private String url;
    private String opis;

    MojeVideo(Integer id_video,String url,String opis){
        this.id_video = id_video;
        this.opis = opis;
        this.url = url;
    }

    public MojeVideo getObj(){
        return this;
    }

    public Integer getId_video() {
        return id_video;
    }

    public void setId_video(Integer id_video) {
        this.id_video = id_video;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }
}
