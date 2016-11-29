package com.example.l03.projektpaszport;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EdycjaPreferencjiActivity extends AppCompatActivity {

    private Button bPreferencje;
    private ListView lvPreferecji;
    private List<Preferencja> preferencje;
    private DatabaseHelper db;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            startActivity(new Intent(getApplicationContext(),PreferencjaActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_preferencji);

        bPreferencje = (Button) findViewById(R.id.bDodajPreferencje);
        lvPreferecji = (ListView) findViewById(R.id.lvPreferencje);

        db = new DatabaseHelper(getApplicationContext());

        preferencje = db.getAllPreferencja();
        Log.e("ilosc osob w liscie", String.valueOf(preferencje.size()));

        final PreferencjaLubieAdapter adapter = new PreferencjaLubieAdapter();
        lvPreferecji.setAdapter(adapter);

        bPreferencje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),DodajPreferencjeActivity.class));
            }
        });

        lvPreferecji.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //edycja osób na kliknięcie na liście
                Intent intent = new Intent(getApplicationContext(),EdytujPreferencjeActivity.class);
                intent.putExtra("preferencja",preferencje.get(position).returnObj());
                finish();
                startActivity(intent);
            }
        });

        lvPreferecji.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { //usuwanie osób na przytrzymanie na liście
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int pozycja = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(EdycjaPreferencjiActivity.this);
                builder.setTitle("czy na pewno chcesz usunąć preferencję?");
                builder.setPositiveButton("tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deletePreferencja(preferencje.get(pozycja).returnObj());
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

    private class PreferencjaLubieAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (preferencje != null && preferencje.size() != 0)
                return preferencje.size();
            return 0;

        }

        @Override
        public Object getItem(int position) {
            return preferencje.get(position);
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
                LayoutInflater inflater = EdycjaPreferencjiActivity.this.getLayoutInflater();
                convertView = inflater.inflate(R.layout.preferencja_listview_item, null);

                holder.opis = (TextView)convertView.findViewById(R.id.tvItemText);
                holder.opis.setText(preferencje.get(position).getOpis());

                holder.zdjecie = (ImageView) convertView.findViewById(R.id.ivItemImage);
                Bitmap bitmap = BitmapFactory.decodeFile(preferencje.get(position).getZdjecie());
                holder.zdjecie.setImageBitmap(bitmap);

                holder.kategoria = (TextView) convertView.findViewById(R.id.tvKategoria);
                holder.helper = (ImageView) convertView.findViewById(R.id.ivHelper);
                if(preferencje.get(position).getLubie()){
                    holder.helper.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.PreferencjeGreen));
                    holder.kategoria.setText("L");
                }
                else{
                    holder.helper.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.PreferencjeRed));
                    holder.kategoria.setText("NL");
                }

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.ref = position;

            return convertView;
        }

        private class ViewHolder {
            ImageView zdjecie;
            ImageView helper;
            TextView kategoria;
            TextView opis;
            int ref;
        }
    }
}
