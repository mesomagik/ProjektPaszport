package com.example.l03.projektpaszport;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class EdycjaOsobActivity extends AppCompatActivity {

    private Button bDodajOsobe;
    private ListView lvListaOsob;
    private List<Osoba> listaOsob;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_osob);

        bDodajOsobe = (Button) findViewById(R.id.bDodajOsobe);
        lvListaOsob = (ListView) findViewById(R.id.lvListaOsob);

        db = new DatabaseHelper(getApplicationContext());

        listaOsob = db.getAllOsoba();
        Log.e("ilosc osob w liscie", String.valueOf(listaOsob.size()));

        final OsobyAdapter adapter = new OsobyAdapter();
        lvListaOsob.setAdapter(adapter);

        bDodajOsobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DodajOsobeActivity.class));
            }
        });

        lvListaOsob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //edycja osób na kliknięcie na liście
                Intent intent = new Intent(getApplicationContext(),EdytujOsobeActivity.class);
                intent.putExtra("osoba",listaOsob.get(position).getObj());
                startActivity(intent);
            }
        });

        lvListaOsob.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { //usuwanie osób na przytrzymanie na liście
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int pozycja = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(EdycjaOsobActivity.this);
                builder.setTitle("czy na pewno chcesz usunąć osobę?");
                builder.setPositiveButton("tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteOsoba(listaOsob.get(pozycja).getObj());
                        finish();
                        startActivity(getIntent());
                    }
                });
                builder.setNeutralButton("nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       //nic nie robi
                    }
                });
                builder.show();

                return false;
            }
        });

    }

    private class OsobyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (listaOsob != null && listaOsob.size() != 0)
                return listaOsob.size();
            return 0;

        }

        @Override
        public Object getItem(int position) {
            return listaOsob.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                LayoutInflater inflater = EdycjaOsobActivity.this.getLayoutInflater();
                convertView = inflater.inflate(R.layout.lista_osoba, null);
                holder.imie_nazwisko = (TextView) convertView.findViewById(R.id.tvImieNazwisko);
                holder.relacja = (TextView) convertView.findViewById(R.id.tvmoje_bezpieczenstwo);
                holder.zdjecie = (ImageView) convertView.findViewById(R.id.ivZdjecie);

                Bitmap bitmap = BitmapFactory.decodeFile(listaOsob.get(position).getZdjecie());
                holder.zdjecie.setImageBitmap(bitmap);

                holder.imie_nazwisko.setText(listaOsob.get(position).getImie_nazwisko());
                holder.relacja.setText(listaOsob.get(position).getRelacja());


                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.ref = position;

            return convertView;
        }

        private class ViewHolder {
            TextView imie_nazwisko;
            TextView relacja;
            ImageView zdjecie;
            int ref;
        }
    }
}
