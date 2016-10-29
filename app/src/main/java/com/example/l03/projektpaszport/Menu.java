package com.example.l03.projektpaszport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {

    private Button bMojeZdrowie;
    private Button bKrotkoOMnie;
    private Button bLubieNieLubie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bMojeZdrowie =(Button) findViewById(R.id.bMojeZdrowie);
        bKrotkoOMnie = (Button) findViewById(R.id.bKrotkoOMnie);
        bLubieNieLubie = (Button) findViewById(R.id.bLubieNieLubie);

        bKrotkoOMnie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OMnieActivity.class));
            }
        });

        bMojeZdrowie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MojeZdrowieActivity.class));
            }
        });

        bLubieNieLubie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PreferencjeActivity.class));
            }
        });
    }
}
