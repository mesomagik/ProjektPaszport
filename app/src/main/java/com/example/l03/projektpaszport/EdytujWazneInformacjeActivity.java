package com.example.l03.projektpaszport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EdytujWazneInformacjeActivity extends AppCompatActivity {

    private EditText etprzyjmowanie_jedzenia;
    private EditText etprzyjmowanie_plynów;
    private EditText etmoje_bezpieczenstwo;
    private EditText etkorzystanie_z_toalety;
    private EditText etopieka_osobista;
    private EditText etsen;
    private EditText etalergie;
    private DatabaseHelper db;
    private WazneInformacje wazneInformacje;
    private Button bEdytuj;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            startActivity(new Intent(getApplicationContext(),OMnieActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_wazne_informacje);

        etprzyjmowanie_jedzenia = (EditText) findViewById(R.id.etprzyjmowanie_jedzenia);
        etprzyjmowanie_plynów= (EditText) findViewById(R.id.etprzyjmowanie_plynów);
        etmoje_bezpieczenstwo= (EditText) findViewById(R.id.etmoje_bezpieczenstwo);
        etkorzystanie_z_toalety= (EditText) findViewById(R.id.etkorzystanie_z_toalety);
        etopieka_osobista= (EditText) findViewById(R.id.etopieka_osobista);
        etsen= (EditText) findViewById(R.id.etsen);
        etalergie= (EditText) findViewById(R.id.etalergie);
        bEdytuj = (Button) findViewById(R.id.bEdytuj);

        db = new DatabaseHelper(getApplicationContext());


        if(!db.checkWazneInformacjeDatabase()) {
            db.createWazneInformacje("brak informacji", "brak informacji", "brak informacji", "brak informacji", "brak informacji", "brak informacji", "brak informacji");
        }
        wazneInformacje = db.getWazneInformacje();
        Log.e("ilosc w wazne", wazneInformacje.getKorzystanie_z_toalety());

        etprzyjmowanie_jedzenia.setText(wazneInformacje.getPrzyjmowanie_jedzenia());
        etprzyjmowanie_plynów.setText(wazneInformacje.getPrzyjmowanie_plynów());
        etmoje_bezpieczenstwo.setText(wazneInformacje.getMoje_bezpieczenstwo());
        etkorzystanie_z_toalety.setText(wazneInformacje.getKorzystanie_z_toalety());
        etopieka_osobista.setText(wazneInformacje.getOpieka_osobista());
        etsen.setText(wazneInformacje.getSen());
        etalergie.setText(wazneInformacje.getAlergie());

        bEdytuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wazneInformacje.setPrzyjmowanie_jedzenia(etprzyjmowanie_jedzenia.getText().toString());
                wazneInformacje.setPrzyjmowanie_plynów(etprzyjmowanie_plynów.getText().toString());
                wazneInformacje.setMoje_bezpieczenstwo(etmoje_bezpieczenstwo.getText().toString());
                wazneInformacje.setKorzystanie_z_toalety(etkorzystanie_z_toalety.getText().toString());
                wazneInformacje.setOpieka_osobista(etopieka_osobista.getText().toString());
                wazneInformacje.setSen(etsen.getText().toString());
                wazneInformacje.setAlergie(etalergie.getText().toString());

                db.updateWazneInformacje(wazneInformacje);

                finish();
                startActivity(new Intent(getApplicationContext(),OMnieActivity.class));
            }
        });

    }
}
