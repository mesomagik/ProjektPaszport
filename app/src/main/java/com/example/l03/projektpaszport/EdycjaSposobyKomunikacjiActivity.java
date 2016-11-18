package com.example.l03.projektpaszport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        if (!db.checkSposobyKomunikacjiDatabase()) {
            db.createSposobyKomunikacji("Dodaj informacje", "Dodaj informacje", "Dodaj informacje");
        }
        sposobyKomunikacji = db.getSposobyKomunikacji();
        Log.e("Moje zmysly: ", sposobyKomunikacji.getMoje_zmysly());

        etMojeZmysly.setText(sposobyKomunikacji.getMoje_zmysly());
        etPrzekazywanieEmocji.setText(sposobyKomunikacji.getPrzekazywanie_emocji());
        etCharakterystyczneZachowania.setText(sposobyKomunikacji.getCharakterystyczne_zachowania());

        bEdytujDane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sposobyKomunikacji.setMoje_zmysly(etMojeZmysly.getText().toString());
                sposobyKomunikacji.setPrzekazywanie_emocji(etPrzekazywanieEmocji.getText().toString());
                sposobyKomunikacji.setCharakterystyczne_zachowania(etCharakterystyczneZachowania.getText().toString());

                db.updateSposobyKomunikacji(sposobyKomunikacji);
                finish();
                startActivity(new Intent(getApplicationContext(), SposobyKomunikacjiActivity.class));
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            startActivity(new Intent(getApplicationContext(), SposobyKomunikacjiActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}