package com.example.l03.projektpaszport;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_OMnie_info_podstawowe.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_OMnie_info_podstawowe#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_OMnie_info_podstawowe extends Fragment {

    private TextView tvprzyjmowanie_jedzenia;
    private TextView tvprzyjmowanie_plynów;
    private TextView tvmoje_bezpieczenstwo;
    private TextView tvkorzystanie_z_toalety;
    private TextView tvopieka_osobista;
    private TextView tvsen;
    private TextView tvalergie;
    private DatabaseHelper db;
    private WazneInformacje wazneInformacje;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment_OMnie_info_podstawowe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Fragment_OMnie_info_podstawowe.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_OMnie_info_podstawowe newInstance() {
        Fragment_OMnie_info_podstawowe fragment = new Fragment_OMnie_info_podstawowe();

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
        View rootView = inflater.inflate(R.layout.fagment__omnie_info_podstawowe, container, false);


          tvprzyjmowanie_jedzenia = (TextView) rootView.findViewById(R.id.tvprzyjmowanie_jedzenia);
          tvprzyjmowanie_plynów = (TextView) rootView.findViewById(R.id.tvprzyjmowanie_plynów);
          tvmoje_bezpieczenstwo = (TextView) rootView.findViewById(R.id.tvmoje_bezpieczenstwo);
          tvkorzystanie_z_toalety = (TextView) rootView.findViewById(R.id.tvkorzystanie_z_toalety);
          tvopieka_osobista = (TextView) rootView.findViewById(R.id.tvopieka_osobista);
          tvsen = (TextView) rootView.findViewById(R.id.tvsen);
          tvalergie = (TextView) rootView.findViewById(R.id.tvalergie);

        db = new DatabaseHelper(getContext());


        if(!db.checkWazneInformacjeDatabase()) {
            db.createWazneInformacje("brak informacji", "brak informacji", "brak informacji", "brak informacji", "brak informacji", "brak informacji", "brak informacji");
        }
        wazneInformacje = db.getWazneInformacje();
        Log.e("ilosc w wazne", wazneInformacje.getKorzystanie_z_toalety());

        tvprzyjmowanie_jedzenia.setText(wazneInformacje.getPrzyjmowanie_jedzenia());
        tvprzyjmowanie_plynów.setText(wazneInformacje.getPrzyjmowanie_plynów());
        tvmoje_bezpieczenstwo.setText(wazneInformacje.getMoje_bezpieczenstwo());
        tvkorzystanie_z_toalety.setText(wazneInformacje.getKorzystanie_z_toalety());
        tvopieka_osobista.setText(wazneInformacje.getOpieka_osobista());
        tvsen.setText(wazneInformacje.getSen());
        tvalergie.setText(wazneInformacje.getAlergie());


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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
