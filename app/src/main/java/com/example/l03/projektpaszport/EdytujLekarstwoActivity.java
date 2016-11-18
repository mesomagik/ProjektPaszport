package com.example.l03.projektpaszport;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Tomek on 2016-11-17.
 */

public class EdytujLekarstwoActivity extends AppCompatActivity {
    private Button bEdytujLekarstwo;
    private Button bZdjecie;
    private EditText etGodzina;
    private EditText etIlosc;
    private EditText etSposobZazycia;
    private ImageView ivZdjecie;
    private String sciezka;
    private DatabaseHelper db;
    Lekarstwo lekarstwo;

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
        if(sciezka == null) {
            sciezka="";
            Bitmap bitmap = BitmapFactory.decodeFile(sciezka);
            ivZdjecie.setImageBitmap(bitmap);
        }else{
            Log.e("sciezka", sciezka);
            Bitmap bitmap = BitmapFactory.decodeFile(sciezka);
            ivZdjecie.setImageBitmap(bitmap);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edytuj_lekarstwo);



        bEdytujLekarstwo= (Button) findViewById(R.id.bEdytujLekarstwo);
        bZdjecie = (Button) findViewById(R.id.bZdjecie);
        etGodzina = (EditText) findViewById(R.id.etGodzina);
        etIlosc = (EditText) findViewById(R.id.etIlosc);
        etSposobZazycia = (EditText) findViewById(R.id.etSposobZazycia);
        ivZdjecie = (ImageView) findViewById(R.id.ivZdjecie);
        db = new DatabaseHelper(getApplicationContext());



        Intent intent = getIntent();
        lekarstwo = (Lekarstwo) intent.getSerializableExtra("lekarstwo");
        sciezka = lekarstwo.getZdjecie();

        Bitmap bitmap = BitmapFactory.decodeFile(lekarstwo.getZdjecie());
        ivZdjecie.setImageBitmap(bitmap);

        etIlosc.setText(lekarstwo.getIlosc());
        etGodzina.setText(lekarstwo.getGodzina());
        etSposobZazycia.setText(lekarstwo.getSposob_zazycia());

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





        bEdytujLekarstwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etSposobZazycia.getText().length()>0
                        && etGodzina.getText().length()>0
                        && etIlosc.getText().length()>0) {

                    lekarstwo.setZdjecie(sciezka);
                    lekarstwo.setGodzina(etGodzina.getText().toString());
                    lekarstwo.setIlosc(etIlosc.getText().toString());
                    lekarstwo.setSposob_zazycia(etSposobZazycia.getText().toString());
                    db.updateLekarstwo(lekarstwo);
                    finish();
                }
            }
        });

    }





    }

