package com.example.l03.projektpaszport;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 2016-11-17.
 */

public class DodajLekarstwoActivity extends AppCompatActivity{
    private Button bDodajLekarstwo;
    private Button bZdjecie;
    private EditText etGodzina;
    private EditText etIlosc;
    private EditText etSposobZazycia;
    private ImageView ivZdjecie;
    private String sciezka;
    private DatabaseHelper db;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Zdjecie zdjecie = new Zdjecie();
        sciezka = zdjecie.getObrazekURI(requestCode,resultCode,data,getApplicationContext());
        Log.e("sciezka",sciezka);
        Bitmap bitmap = BitmapFactory.decodeFile(sciezka);
        ivZdjecie.setImageBitmap(bitmap);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_lekarstwo);

        bDodajLekarstwo= (Button) findViewById(R.id.bDodajLekarstwo);
        bZdjecie = (Button) findViewById(R.id.bZdjecie);
        etGodzina = (EditText) findViewById(R.id.etGodzina);
        etIlosc = (EditText) findViewById(R.id.etIlosc);
        etSposobZazycia = (EditText) findViewById(R.id.etSposobZazycia);
        ivZdjecie = (ImageView) findViewById(R.id.ivZdjecie);
        db = new DatabaseHelper(getApplicationContext());

        bZdjecie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT <= 19) {
                    Intent i = new Intent();
                    i.setType("image/*");
                    i.setAction(Intent.ACTION_GET_CONTENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(i, 10);
                } else if (Build.VERSION.SDK_INT > 19) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 10);
                }
            }
        });



        bDodajLekarstwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etIlosc.getText().length()>0
                        && sciezka.length()>0
                        && etGodzina.getText().length()>0
                        && etSposobZazycia.getText().length()>0) {
                    db.createLekarstwo(etGodzina.getText().toString(), etIlosc.getText().toString(),
                            etSposobZazycia.getText().toString(),
                            sciezka);
                    finish();

                }
            }
        });

    }
}
