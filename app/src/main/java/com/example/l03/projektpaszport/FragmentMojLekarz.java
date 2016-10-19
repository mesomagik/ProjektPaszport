package com.example.l03.projektpaszport;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMojLekarz.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMojLekarz#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMojLekarz extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private TextView tvImie;
    private DatabaseHelper db;
    private ArrayList<String> lekarz;
    private Button bRef;


    private OnFragmentInteractionListener mListener;

    public FragmentMojLekarz() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment FragmentMojLekarz.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMojLekarz newInstance() {
        FragmentMojLekarz fragment = new FragmentMojLekarz();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_moj_lekarz, container, false);

        tvImie = (TextView) rootView.findViewById(R.id.tvImie);
        bRef = (Button) rootView.findViewById(R.id.bRef);


        lekarz = db.getLekarz();
        if(lekarz.isEmpty()) {
            db.createLekarz("imie lekarza", "nazwisko lekarza");
            lekarz = db.getLekarz();
        }

        tvImie.setText(lekarz.get(0));
        bRef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),MojeZdrowieActivity.class));
            }
        });

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

        db = new DatabaseHelper(context);

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
