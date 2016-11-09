package com.example.l03.projektpaszport;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

public class EdytujOsobeActivity extends AppCompatActivity {

    private Button bUstawDate;
    private Button bEdytujOsobe;
    private Button bZdjecie;
    private Spinner spRelacja;
    private EditText etKontakt;
    private EditText etImieNazwisko;
    private EditText etInformacje;
    private ImageView ivZdjecie;
    private String sciezka;
    public static TextView tvDataUrodzenia;
    private List<String> relacjaList;
    private DatabaseHelper db;
    Osoba osoba;

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
        setContentView(R.layout.activity_edytuj_osobe);

        bUstawDate = (Button) findViewById(R.id.bUstawDate);
        bEdytujOsobe = (Button) findViewById(R.id.bEdytujOsobe);
        bZdjecie = (Button) findViewById(R.id.bZdjecie);
        spRelacja = (Spinner) findViewById(R.id.spRelacja);
        etKontakt = (EditText) findViewById(R.id.etKontakt);
        etImieNazwisko = (EditText) findViewById(R.id.etImieNazwisko);
        etInformacje = (EditText) findViewById(R.id.etInformacje);
        ivZdjecie = (ImageView) findViewById(R.id.ivZdjecie);
        tvDataUrodzenia = (TextView) findViewById(R.id.tvprzyjmowanie_plynów);
        db = new DatabaseHelper(getApplicationContext());


        Intent intent = getIntent();
        osoba = (Osoba) intent.getSerializableExtra("osoba");
        sciezka = osoba.getZdjecie();

        Bitmap bitmap = BitmapFactory.decodeFile(osoba.getZdjecie());
        ivZdjecie.setImageBitmap(bitmap);

        etImieNazwisko.setText(osoba.getImie_nazwisko());
        etInformacje.setText(osoba.getInformacje());
        etKontakt.setText(osoba.getKontakt());
        tvDataUrodzenia.setText(osoba.getData_ur());

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


        bUstawDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new Data();
                dialogFragment.show(getFragmentManager(), "datePicker");

            }
        });

        relacjaList = new ArrayList<>();

        if(osoba.getRelacja().equals("pacjent")){
            relacjaList.add("pacjent");
        }

        relacjaList.add("rodzina");
        relacjaList.add("lekarz");
        relacjaList.add("przyjaciel");
        relacjaList.add("opiekun");



        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,relacjaList);

        spRelacja.setAdapter(adapter);

        for(int i=0; i<relacjaList.size();i++){
            if(relacjaList.get(i).equals(osoba.getRelacja())){
                spRelacja.setSelection(i);
            }
        }

        bEdytujOsobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etImieNazwisko.getText().length()>0
                        && etInformacje.getText().length()>0
                        && !tvDataUrodzenia.getText().equals("Data urodzenia")
                        && etKontakt.getText().length()>0) {

                    osoba.setZdjecie(sciezka);
                    osoba.setImie_nazwisko(etImieNazwisko.getText().toString());
                    osoba.setData_ur(tvDataUrodzenia.getText().toString());
                    osoba.setInformacje(etInformacje.getText().toString());
                    osoba.setKontakt(etKontakt.getText().toString());
                    osoba.setRelacja(spRelacja.getSelectedItem().toString());

                    db.updateOsoba(osoba);
                    finish();
                    startActivity(new Intent(getApplicationContext(), EdycjaOsobActivity.class));
                }
            }
        });

    }

    public static class Data extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            tvDataUrodzenia.setText(String.valueOf(day)+"-"+String.valueOf(month+1)+"-"+String.valueOf(year));
        }



    }
}
