package com.example.l03.projektpaszport;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SzczegolyOsobyActivity extends AppCompatActivity {

    private TextView tvImieNazwisko;
    private TextView tvKontakt;
    private TextView tvInformacje;
    private TextView tvDataUrodzenia;
    private TextView tvRelacja;
    private ImageView ivZdjecie;
    private Osoba osoba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szczegoly_osoby);

        tvDataUrodzenia = (TextView) findViewById(R.id.tvDataUrodzenia);
        tvImieNazwisko = (TextView) findViewById(R.id.tvImie);
        tvKontakt = (TextView) findViewById(R.id.tvKontakt);
        tvInformacje = (TextView) findViewById(R.id.tvInformacje);
        tvRelacja = (TextView) findViewById(R.id.tvRelacja);
        ivZdjecie = (ImageView) findViewById(R.id.ivZdjecie);

        Intent intent = getIntent();
        osoba = (Osoba) intent.getSerializableExtra("osoba");

        Bitmap bitmap = BitmapFactory.decodeFile(osoba.getZdjecie());
        ivZdjecie.setImageBitmap(bitmap);

        tvImieNazwisko.setText(osoba.getImie_nazwisko());
        tvRelacja.setText(osoba.getRelacja());
        tvDataUrodzenia.setText("Data urodzenia: "+osoba.getData_ur());
        tvInformacje.setText(osoba.getInformacje());
        tvKontakt.setText(osoba.getKontakt());

    }
}
