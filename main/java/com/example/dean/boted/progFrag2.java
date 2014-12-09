package com.example.dean.boted;

import android.app.Activity;
import android.bluetooth.BluetoothSocket;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {link progFrag2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link progFrag2#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class progFrag2 extends Fragment {

    ArrayList<String> program_list;
    ArrayAdapter<String> adapt;
    BluetoothSocket mmSocket;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment progFrag2.
     */
    public static progFrag2 newInstance() {
        progFrag2 fragment = new progFrag2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    public progFrag2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        program_list = new ArrayList<String>();
        adapt = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, program_list);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prog_frag2, container, false);
        ListView lv = (ListView) view.findViewById(R.id.list);
        lv.setAdapter(adapt);

        ImageView play_button = (ImageView) view.findViewById(R.id.play_button);
        play_button.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    ConnectedThread t = new ConnectedThread(mmSocket);
                    t.run(program_list);
                }
            }
        );

        ImageView stop_button = (ImageView) view.findViewById(R.id.stop_button);
        stop_button.setOnClickListener( new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v)
                                            {
                ConnectedThread t = new ConnectedThread(mmSocket);
                t.stop_it();
            }
        }
        );

        Button clear_all = (Button) view.findViewById(R.id.clear_all);
        clear_all.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                program_list.clear();
                ((BaseAdapter) adapt).notifyDataSetChanged();
            }
        }


        );


        return view;
    }

    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
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
//    public interface OnFragmentInteractionListener {
//        public void onFragmentInteraction(Uri uri);
//    }

}
