package com.example.l03.projektpaszport;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EdytujPreferencjeActivity extends AppCompatActivity {

    private Button bEdytujPreferencje;
    private Button bZdjecie;
    private RadioGroup radioGroup;
    private RadioButton lubie,nielubie;
    private EditText etOpis;
    private ImageView ivZdjecie;
    private String sciezka;
    private DatabaseHelper db;
    Preferencja preferencja;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            startActivity(new Intent(getApplicationContext(),EdycjaPreferencjiActivity.class));
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
        setContentView(R.layout.activity_edytuj_preferencje);

        bEdytujPreferencje = (Button) findViewById(R.id.bEdytujPreferencje);
        ivZdjecie = (ImageView) findViewById(R.id.ivZdjecie);
        bZdjecie = (Button) findViewById(R.id.bZdjecie);
        radioGroup = (RadioGroup) findViewById(R.id.rgRadioGroup);
        lubie = (RadioButton) findViewById(R.id.rbLubie);
        nielubie = (RadioButton) findViewById(R.id.rbNieLubie);
        etOpis = (EditText) findViewById(R.id.etOpis);
        db = new DatabaseHelper(getApplicationContext());

        Intent intent = getIntent();
        preferencja = (Preferencja) intent.getSerializableExtra("preferencja");
        sciezka = preferencja.getZdjecie();

        Bitmap bitmap = BitmapFactory.decodeFile(sciezka);
        ivZdjecie.setImageBitmap(bitmap);

        if(preferencja.getLubie()){
            lubie.setChecked(true);
            nielubie.setChecked(false);
        }else{
            lubie.setChecked(false);
            nielubie.setChecked(true);
        }
        etOpis.setText(preferencja.getOpis());

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

        bEdytujPreferencje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etOpis.getText().length()>0
                        && sciezka.length()>0
                        && (lubie.isChecked() || nielubie.isChecked())){

                    preferencja.setZdjecie(sciezka);
                    preferencja.setLubie(lubie.isChecked());
                    preferencja.setOpis(etOpis.getText().toString());

                    db.updatePreferencja(preferencja);
                    finish();
                }
            }
        });
    }
}
