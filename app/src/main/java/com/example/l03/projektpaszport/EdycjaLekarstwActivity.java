package com.example.l03.projektpaszport;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

import java.util.List;

/**
 * Created by Tomek on 2016-11-17.
 */

public class EdycjaLekarstwActivity extends AppCompatActivity {

    private Button bDodajLekarstwo;
    private ListView lvListaLekarstw;
    private List<Lekarstwo> listaLekarstw;
    private DatabaseHelper db;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            startActivity(new Intent(getApplicationContext(),MojeZdrowieActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_lekarstw);

        bDodajLekarstwo = (Button) findViewById(R.id.bDodajLekarstwo);
        lvListaLekarstw = (ListView) findViewById(R.id.lvListaLekarstw);

        db = new DatabaseHelper(getApplicationContext());

        listaLekarstw = db.getAllLekarstwo();
        Log.e("ilosc lekarstw w liscie", String.valueOf(listaLekarstw.size()));

        final LekarstwaAdapter adapter = new LekarstwaAdapter();
        lvListaLekarstw.setAdapter(adapter);

        bDodajLekarstwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),DodajLekarstwoActivity.class));
            }
        });

        lvListaLekarstw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),EdytujLekarstwoActivity.class);
                intent.putExtra("lekarstwo",listaLekarstw.get(position).returnObj());
                startActivity(intent);
            }
        });

        lvListaLekarstw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { //usuwanie osób na przytrzymanie na liście
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final int pozycja = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(EdycjaLekarstwActivity.this);
                builder.setTitle("czy na pewno chcesz usunąć lekarstwo?");
                builder.setPositiveButton("tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteLekarstwo(listaLekarstw.get(pozycja).returnObj());
                        finish();
                        startActivity(getIntent());
                    }
                });
                builder.setNeutralButton("nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();

                return false;
            }
        });

    }

    private class LekarstwaAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (listaLekarstw != null && listaLekarstw.size() != 0)
                return listaLekarstw.size();
            return 0;

        }

        @Override
        public Object getItem(int position) {
            return listaLekarstw.get(position);
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
                LayoutInflater inflater = EdycjaLekarstwActivity.this.getLayoutInflater();
                convertView = inflater.inflate(R.layout.lista_lekarstwo, null);
                holder.ilosc = (TextView) convertView.findViewById(R.id.tvIlosc);
                holder.godzina = (TextView) convertView.findViewById(R.id.tvGodzina);
                holder.sposobUzycia = (TextView) convertView.findViewById(R.id.tvSposobUzycia);
                holder.zdjecie = (ImageView) convertView.findViewById(R.id.ivZdjecie);

                Bitmap bitmap = BitmapFactory.decodeFile(listaLekarstw.get(position).getZdjecie());
                holder.zdjecie.setImageBitmap(bitmap);

                holder.ilosc.setText("Ilość: "+listaLekarstw.get(position).getIlosc());
                holder.godzina.setText("Godzina: " +listaLekarstw.get(position).getGodzina());
                holder.sposobUzycia.setText("Sposób zażycia: " +listaLekarstw.get(position).getSposob_zazycia());
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.ref = position;

            return convertView;
        }

        private class ViewHolder {
            TextView ilosc;
            TextView godzina;
            TextView sposobUzycia;
            ImageView zdjecie;
            int ref;
        }
    }
}
