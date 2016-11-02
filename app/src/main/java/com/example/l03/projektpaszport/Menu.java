package com.example.l03.projektpaszport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button bMojeZdrowie;
    private Button bKrotkoOMnie;
    private Button bSposobyKomunikacji;
    private Button bLubieNieLubie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bMojeZdrowie = (Button) findViewById(R.id.bMojeZdrowie);
        bKrotkoOMnie = (Button) findViewById(R.id.bKrotkoOMnie);
        bSposobyKomunikacji = (Button) findViewById(R.id.bSposobyKomunikacji);
        bLubieNieLubie = (Button) findViewById(R.id.bLubieNieLubie);

        bKrotkoOMnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OMnieActivity.class));
            }
        });

        bMojeZdrowie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MojeZdrowieActivity.class));
            }
        });

        bSposobyKomunikacji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SposobyKomunikacjiActivity.class));
            }
        });
        bLubieNieLubie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PreferencjaActivity.class));
            }
        });
    }
}
