package com.example.l03.projektpaszport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_OMnie_ja.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_OMnie_ja#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_OMnie_ja extends Fragment {

    private Osoba osoba;
    private DatabaseHelper db;
    private List<Osoba> listaOsob;
    private TextView tvImieNazwisko;
    private TextView tvDataUrodzenia;
    private TextView tvKontakt;
    private ImageView ivPacjent;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    public Fragment_OMnie_ja() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Fragment_OMnie_ja.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_OMnie_ja newInstance() {
        Fragment_OMnie_ja fragment = new Fragment_OMnie_ja();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fragment__omnie_ja, container, false);


        tvImieNazwisko = (TextView) rootView.findViewById(R.id.tvImieNazwisko);
        tvKontakt = (TextView) rootView.findViewById(R.id.tvKontakt);
        tvDataUrodzenia = (TextView) rootView.findViewById(R.id.tvprzyjmowanie_plynów);
        ivPacjent = (ImageView) rootView.findViewById(R.id.ivPacjent);


        db = new DatabaseHelper(getContext());

        listaOsob = db.getAllOsobaByRelacja("pacjent");


        if(listaOsob.size()==1){
            Bitmap bitmap = BitmapFactory.decodeFile(listaOsob.get(0).getZdjecie());
            ivPacjent.setImageBitmap(bitmap);

            tvDataUrodzenia.setText("Data urodzenia: "+listaOsob.get(0).getData_ur());
            tvKontakt.setText(listaOsob.get(0).getKontakt());
            tvImieNazwisko.setText(listaOsob.get(0).getImie_nazwisko());
        }

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

}
