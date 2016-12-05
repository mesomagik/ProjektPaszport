package com.example.l03.projektpaszport;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class EdycjaSposobyKomunikacjiDzwiekActivity extends AppCompatActivity {

    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = "/sdcard/audiotest.3gp";

    private Button mRecordButton = null;
    MediaRecorder mRecorder = new MediaRecorder();

    private Button mPlayButton = null;
    private MediaPlayer mPlayer = new MediaPlayer();

    boolean mStartRecording = true;
    boolean mStartPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edycja_sposoby_komunikacji_dzwiek);

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/przekazywanie_emocji.3gp";

        mRecordButton = (Button) findViewById(R.id.bNagraj);
        mPlayButton = (Button) findViewById(R.id.bOdtworz);
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                mStartPlaying = !mStartPlaying;
            }
        });
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.setLooping(true);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        } finally {
            mPlayButton.setText("Zatrzymaj");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
        mPlayButton.setText("Odtworz");
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
        mRecordButton.setText("Zakończ nagrywanie");
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.reset();
        mRecorder.release();
        mRecorder = null;
        mRecordButton.setText("Nagraj");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            startActivity(new Intent(getApplicationContext(), SposobyKomunikacjiActivity.class));
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
