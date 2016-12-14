package com.example.l03.projektpaszport;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;

public class EdycjaVideoActivity extends AppCompatActivity {

    private Button bDodajFilm;
    private ListView lvVideo;
    private DatabaseHelper db;
    private List<MojeVideo> listVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_video);

        bDodajFilm = (Button) findViewById(R.id.bDodajFilm);
        lvVideo = (ListView) findViewById(R.id.lvVideo);
        db = new DatabaseHelper(getApplicationContext());
        listVideo = db.getAllMojeVideo();


        final VideoAdapter adapter = new VideoAdapter();
        lvVideo.setAdapter(adapter);

        bDodajFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DodajVideoActivity.class));
                finish();
            }
        });

    }

    private class VideoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (listVideo != null && listVideo.size() != 0)
                return listVideo.size();
            return 0;

        }

        @Override
        public Object getItem(int position) {
            return listVideo.get(position);
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                final LayoutInflater inflater =  LayoutInflater.from(getApplicationContext());

                convertView = inflater.inflate(R.layout.lista_video_edycja, null);
                holder.bWyswietl = (Button) convertView.findViewById(R.id.bWyswietl);
                holder.opis = (TextView) convertView.findViewById(R.id.tvVideoOpis);
                holder.bEdytuj = (Button) convertView.findViewById(R.id.bEdytuj);
                holder.bUsun = (Button) convertView.findViewById(R.id.bUsun);

                holder.opis.setText(listVideo.get(position).getOpis());

                holder.bWyswietl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),PokazVideoActivity.class);
                        intent.putExtra("video",listVideo.get(position).getObj());
                        startActivity(intent);
                    }
                });

                holder.bUsun.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(EdycjaVideoActivity.this);
                        builder.setTitle("czy na pewno chcesz usunąć film?");
                        builder.setPositiveButton("tak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.deleteVideo(listVideo.get(position).getObj());
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
                    }
                });

                holder.bEdytuj.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),EdytujVideoActivity.class);
                        intent.putExtra("video",listVideo.get(position).getObj());
                        finish();
                        startActivity(intent);
                    }
                });



                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.ref = position;

            return convertView;
        }

        private class ViewHolder {
            Button bWyswietl;
            Button bEdytuj;
            TextView opis;
            Button bUsun;
            int ref;
        }
    }

}
