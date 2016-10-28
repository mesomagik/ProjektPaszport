package com.example.l03.projektpaszport;

import java.io.Serializable;

/**
 * Created by Bartek on 2016-10-24.
 */
public class SposobyKomunikacji implements Serializable {


    private String moje_zmysly;
    private String charakterystyczne_zachowania;
    private String przekazywanie_emocji;

    SposobyKomunikacji(
            String moje_zmysly,
            String charakterystyczne_zachowania,
            String przekazywanie_emocji
    ) {
        this.moje_zmysly = moje_zmysly;
        this.charakterystyczne_zachowania = charakterystyczne_zachowania;
        this.przekazywanie_emocji = przekazywanie_emocji;
    }

    public String getPrzekazywanie_emocji() {
        return przekazywanie_emocji;
    }

    public void setPrzekazywanie_emocji(String przekazywanie_emocji) {
        this.przekazywanie_emocji = przekazywanie_emocji;
    }

    public String getMoje_zmysly() {
        return moje_zmysly;
    }

    public void setMoje_zmysly(String moje_zmysly) {
        this.moje_zmysly = moje_zmysly;
    }

    public String getCharakterystyczne_zachowania() {
        return charakterystyczne_zachowania;
    }

    public void setCharakterystyczne_zachowania(String charakterystyczne_zachowania) {
        this.charakterystyczne_zachowania = charakterystyczne_zachowania;
    }

    public SposobyKomunikacji returnObj() {
        return this;
    }
}