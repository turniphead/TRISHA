package com.example.dean.boted;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.dean.boted.dummy.DummyContent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the {link Callbacks}
 * interface.
 */
public class progFragment extends ListFragment {

    //private ArrayList<String> progList = new ArrayList<String>();
    //private ListAdapter progAdapter = new ArrayAdapter(getActivity(),
            //android.R.layout.simple_list_item_1, android.R.id.text1);
    //private OnFragmentInteractionListener mListener;

    private ListAdapter mAdapter;
    private AbsListView mListView;

    public List<String> program_list;
    public ListAdapter progAdapter;

    //public ListAdapter progAdapter = new ArrayAdapter(getActivity(),
    //        android.R.layout.simple_list_item_1, android.R.id.text1, program_list);
    private static final String ARG_PARAM1 = "program_array";


    public static progFragment newInstance(ArrayList<String> currentProgram) {
        progFragment fragment = new progFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, currentProgram);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public progFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            program_list = getArguments().getStringArrayList(ARG_PARAM1);
        }

        //ArrayList<String> program_list = new ArrayList<String>();
        mAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, program_list);
        //setListAdapter(progAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_func, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(R.id.func_list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        //mListView.setOnItemClickListener(this);

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//
//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
//        }
//    }

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
//        public void onFragmentInteraction(String id);
//    }

}
