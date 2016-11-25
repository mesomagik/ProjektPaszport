package com.example.l03.projektpaszport;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.media.MediaRecorder;
import android.media.MediaPlayer;
import android.widget.Button;
import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_SposobyKomunikacji_przekazywanie_emocji.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_SposobyKomunikacji_przekazywanie_emocji#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_SposobyKomunikacji_przekazywanie_emocji extends Fragment {

    private TextView tvPrzekazywanieEmocji;
    private DatabaseHelper db;
    private SposobyKomunikacji sposobyKomunikacji;

    private static final String LOG_TAG = "AudioRecordTest";
    private static String mFileName = "/sdcard/audiotest.3gp";

    private Button mRecordButton = null;
    MediaRecorder mRecorder = new MediaRecorder();

    private Button mPlayButton = null;
    private MediaPlayer mPlayer = new MediaPlayer();
    FileInputStream fis = null;

    boolean mStartRecording = true;
    boolean mStartPlaying = true;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_SposobyKomunikacji_przekazywanie_emocji() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_SposobyKomunikacji_przekazywanie_emocji.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_SposobyKomunikacji_przekazywanie_emocji newInstance(String param1, String param2) {
        Fragment_SposobyKomunikacji_przekazywanie_emocji fragment = new Fragment_SposobyKomunikacji_przekazywanie_emocji();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static Fragment_SposobyKomunikacji_przekazywanie_emocji newInstance() {
        Fragment_SposobyKomunikacji_przekazywanie_emocji fragment = new Fragment_SposobyKomunikacji_przekazywanie_emocji();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
        mFileName += "/przekazywanie_emocji.3gp";

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_sposoby_komunikacji_przekazywanie_emocji, container, false);
        tvPrzekazywanieEmocji = (TextView) rootView.findViewById(R.id.tvPrzekazywanieEmocji);

        mRecordButton = (Button) rootView.findViewById(R.id.bNagraj);
        mRecordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onRecord(mStartRecording);
                mStartRecording = !mStartRecording;
            }
        });
        mPlayButton = (Button) rootView.findViewById(R.id.bOdtworz);
        mPlayButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                mStartPlaying = !mStartPlaying;
            }
        });

        db = new DatabaseHelper(getContext());

        sposobyKomunikacji = db.getSposobyKomunikacji();
        if (sposobyKomunikacji != null)
            tvPrzekazywanieEmocji.setText(sposobyKomunikacji.getPrzekazywanie_emocji());
        else
            tvPrzekazywanieEmocji.setText("Dodaj informacje");
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // AUDIO //

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


}
