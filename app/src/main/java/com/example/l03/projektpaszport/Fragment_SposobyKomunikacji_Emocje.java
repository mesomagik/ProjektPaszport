package com.example.l03.projektpaszport;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_SposobyKomunikacji_Emocje.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_SposobyKomunikacji_Emocje#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_SposobyKomunikacji_Emocje extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private ListView lvEmocje;
    private List<MojeVideo> listVideo;
    private DatabaseHelper db;

    private OnFragmentInteractionListener mListener;

    public Fragment_SposobyKomunikacji_Emocje() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment_SposobyKomunikacji_Emocje.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_SposobyKomunikacji_Emocje newInstance() {
        Fragment_SposobyKomunikacji_Emocje fragment = new Fragment_SposobyKomunikacji_Emocje();
        Bundle args = new Bundle();

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
        View rootView =  inflater.inflate(R.layout.fragment__sposoby_komunikacji__emocje, container, false);

        db = new DatabaseHelper(getContext());

        lvEmocje = (ListView) rootView.findViewById(R.id.lvEmocje);
        listVideo = db.getAllMojeVideo();

        final VideoAdapter adapter = new VideoAdapter();
        lvEmocje.setAdapter(adapter);




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
                final LayoutInflater inflater =  LayoutInflater.from(getContext());

                convertView = inflater.inflate(R.layout.lista_video, null);
                holder.bWyswietl = (Button) convertView.findViewById(R.id.bWyswietl);
                holder.opis = (TextView) convertView.findViewById(R.id.tvVideoOpis);

                holder.opis.setText(listVideo.get(position).getOpis());

                holder.bWyswietl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(),PokazVideoActivity.class);
                        intent.putExtra("video",listVideo.get(position).getObj());
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
            TextView opis;
            int ref;
        }
    }
}
