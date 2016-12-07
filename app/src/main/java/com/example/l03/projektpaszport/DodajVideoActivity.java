package com.example.l03.projektpaszport;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DodajVideoActivity extends AppCompatActivity {

    private Button bWybierzFilm;
    private EditText etOpis;
    private Button bDodaj;
    private DatabaseHelper db;
    private String sciezka ="";

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            startActivity(new Intent(getApplicationContext(),EdycjaVideoActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Zdjecie zdjecie = new Zdjecie();
        sciezka = zdjecie.getObrazekURI(requestCode,resultCode,data,getApplicationContext());
        if (sciezka==null){
            sciezka="";
        }else{
            Toast.makeText(DodajVideoActivity.this, "wybrano film", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_video);

        bWybierzFilm = (Button) findViewById(R.id.bWybierzFilm);
        etOpis = (EditText) findViewById(R.id.etOpis);
        bDodaj = (Button) findViewById(R.id.bDodaj);
        db = new DatabaseHelper(getApplicationContext());

        bWybierzFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT <= 19) {
                    Intent i = new Intent();
                    i.setType("video/*");
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(i, 10);
                } else if (Build.VERSION.SDK_INT > 19) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 10);
                }
            }
        });

        bDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sciezka.length()>0 && etOpis.getText().toString().length()>0){
                    db.createVideo(sciezka,etOpis.getText().toString());
                    startActivity(new Intent(getApplicationContext(),EdycjaVideoActivity.class));
                    finish();
                }else{
                    Toast.makeText(DodajVideoActivity.this, "wypełnij wszystkie dane", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
