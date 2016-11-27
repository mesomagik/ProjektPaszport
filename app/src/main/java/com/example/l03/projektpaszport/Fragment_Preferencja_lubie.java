package com.example.l03.projektpaszport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Krzysiek on 02.11.2016.
 */

public class Fragment_Preferencja_lubie extends Fragment {

    private OnFragmentInteractionListener mListener;

    private ListView listView;
    private List<Preferencja> preferencje;
    private DatabaseHelper db;

    public Fragment_Preferencja_lubie() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Fragment_Preferencja_lubie.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Preferencja_lubie newInstance() {
        Fragment_Preferencja_lubie fragment = new Fragment_Preferencja_lubie();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_preferencja_lubie, container, false);
        listView = (ListView) rootView.findViewById(R.id.lvLubie);

        db = new DatabaseHelper(getContext());

        preferencje = db.getAllPreferencjaByLubie(true);

        final PreferencjaLubieAdapter adapter = new PreferencjaLubieAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("naciśnięto element","true");
            }
        });


/*        gridView.setOnClickListener(new View.OnClickListener() {  //powinno być setOnItemClickListener bo wybierasz item z listy
            @Override
            public void onClick(View v) {
                Log.e("naciśnięto element","true");
            }
        });

        */
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
                LayoutInflater inflater = LayoutInflater.from(getActivity()); //byłem tak blisko...
                convertView = inflater.inflate(R.layout.preferencja_listview_item, null);

                holder.opis = (TextView)convertView.findViewById(R.id.tvItemText);
                holder.opis.setText(preferencje.get(position).getOpis());

                holder.zdjecie = (ImageView) convertView.findViewById(R.id.ivItemImage);
                Bitmap bitmap = BitmapFactory.decodeFile(preferencje.get(position).getZdjecie());
                holder.zdjecie.setImageBitmap(bitmap);

                //holder.helper = (ImageView) convertView.findViewById(R.id.ivHelper);
                //holder.helper.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.PreferencjeGreen));

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
            TextView opis;
            int ref;
        }
    }
}
