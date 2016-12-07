package com.example.l03.projektpaszport;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class PokazVideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private MojeVideo mojeVideo;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokaz_video);

        videoView = (VideoView) findViewById(R.id.videoView);

        Intent intent = getIntent();
        mojeVideo = (MojeVideo) intent.getSerializableExtra("video");
        Log.e("video",mojeVideo.getUrl());

        MediaController mc = new MediaController(PokazVideoActivity.this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri uri = Uri.parse(mojeVideo.getUrl());
        videoView.setMediaController(mc);
        videoView.setVideoURI(uri);

        videoView.start();

    }


}
