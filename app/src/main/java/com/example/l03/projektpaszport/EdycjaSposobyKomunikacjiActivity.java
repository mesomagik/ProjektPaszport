package com.example.l03.projektpaszport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EdycjaSposobyKomunikacjiActivity extends AppCompatActivity {

    private EditText etMojeZmysly;
    private EditText etPrzekazywanieEmocji;
    private EditText etCharakterystyczneZachowania;
    private Button bEdytujDane;
    private DatabaseHelper db;
    SposobyKomunikacji sposobyKomunikacji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_sposoby_komunikacji);

        etMojeZmysly = (EditText) findViewById(R.id.etMojeZmysly);
        etPrzekazywanieEmocji = (EditText) findViewById(R.id.etPrzekazywanieEmocji);
        etCharakterystyczneZachowania = (EditText) findViewById(R.id.etCharakterystyczneZachowania);
        bEdytujDane = (Button) findViewById(R.id.bEdytujDane);

        db = new DatabaseHelper(getApplicationContext());

        sposobyKomunikacji = (SposobyKomunikacji) db.getSposobyKomunikacji();
        if (sposobyKomunikacji != null)
            etMojeZmysly.setText(sposobyKomunikacji.getMoje_zmysly());
        else
            etMojeZmysly.setText("Dodaj opis");
        if (sposobyKomunikacji != null)
            etPrzekazywanieEmocji.setText(sposobyKomunikacji.getPrzekazywanie_emocji());
        else
            etPrzekazywanieEmocji.setText("Dodaj opis");
        if (sposobyKomunikacji != null)
            etCharakterystyczneZachowania.setText(sposobyKomunikacji.getCharakterystyczne_zachowania());
        else
            etCharakterystyczneZachowania.setText("Dodaj opis");

        bEdytujDane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etMojeZmysly.getText().length() > 0
                        && etPrzekazywanieEmocji.getText().length() > 0
                        && etCharakterystyczneZachowania.getText().length() > 0) {
                    if (sposobyKomunikacji != null) {
                        sposobyKomunikacji.setMoje_zmysly(etMojeZmysly.getText().toString());
                        sposobyKomunikacji.setPrzekazywanie_emocji(etPrzekazywanieEmocji.getText().toString());
                        sposobyKomunikacji.setCharakterystyczne_zachowania(etCharakterystyczneZachowania.getText().toString());

                        db.updateSposobyKomunikacji(sposobyKomunikacji);
                        finish();
                    } else {
                        db.createSposobyKomunikacji(etMojeZmysly.getText().toString(),etPrzekazywanieEmocji.getText().toString(),etCharakterystyczneZachowania.getText().toString());
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}