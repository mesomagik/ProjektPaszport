package com.example.l03.projektpaszport;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * Created by Tomek on 2016-11-17.
 */

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_MojeZdrowie_Lekarstwa.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_MojeZdrowie_Lekarstwa#newInstance} factory method to
 * create an instance of this fragment.
 */

public class Fragment_MojeZdrowie_Lekarstwa extends Fragment{
    private Button bDodajLekarstwo;
    private ListView lvListaLekarstw;
    private List<Lekarstwo> listaLekarstw;
    private DatabaseHelper db;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Fragment_MojeZdrowie_Lekarstwa.OnFragmentInteractionListener mListener;

    public  Fragment_MojeZdrowie_Lekarstwa() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Fragment_MojeZdrowie_Lekarstwa.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_MojeZdrowie_Lekarstwa newInstance() {
        Fragment_MojeZdrowie_Lekarstwa fragment = new Fragment_MojeZdrowie_Lekarstwa();

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

        View rootView = inflater.inflate(R.layout.fragment_mojezdrowie_lekarstwa, container, false);


        bDodajLekarstwo = (Button) rootView.findViewById(R.id.bDodajLekarstwo);
        lvListaLekarstw= (ListView) rootView.findViewById(R.id.lvListaLekarstw);

        db = new DatabaseHelper(getContext());
        listaLekarstw = db.getAllLekarstwo();


        Log.e("ilosc lekarstw w liscie", String.valueOf(listaLekarstw.size()));

        final LekarstwaAdapter adapter = new LekarstwaAdapter();
        lvListaLekarstw.setAdapter(adapter);




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
        if (context instanceof Fragment_MojeZdrowie_Lekarstwa.OnFragmentInteractionListener) {
            mListener = (Fragment_MojeZdrowie_Lekarstwa.OnFragmentInteractionListener) context;
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
                LayoutInflater inflater =LayoutInflater.from(getActivity());
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
