package com.example.dean.boted;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;


import com.example.dean.boted.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p />
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p />
 * Activities containing this fragment MUST implement the  Callbacks
 * interface.
 */
public class FuncFragment extends Fragment implements AbsListView.OnItemClickListener {



    private List<String> func_array;
    private OnFuncSelectedListener mListener;
    private String m_text = "";
    private String x_text = "";
    private String y_text = "";


    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    public static FuncFragment newInstance() {
        FuncFragment fragment = new FuncFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FuncFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        func_array = Arrays.asList(getResources().getStringArray(R.array.function_list));

        mAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, func_array);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_func, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(R.id.func_list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFuncSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

//    public void setFlag() {
//        dialogue_flag = 1;
//    }

    public void getParam(String var_name) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity());

        alert.setTitle("Input");
        alert.setMessage("Enter a Value for " + var_name);

        // Set an EditText view to get user input
        final EditText input = new EditText(this.getActivity());
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Accepted
                m_text = input.getText().toString();
                //Log.v("edittext", m_text);
                //mListener.onFuncSelected(command.replace("input", m_text));
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
                return;
            }
        });

        alert.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final String command = func_array.get(position);
        String input_text = null;

        if (command.contains("(input)")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this.getActivity());

            alert.setTitle("Input");
            alert.setMessage("Enter a Value for X");

            // Set an EditText view to get user input
            final EditText input = new EditText(this.getActivity());
            alert.setView(input);

            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Accepted
                    m_text = input.getText().toString();
                    //Log.v("edittext", m_text);
                    mListener.onFuncSelected(command.replace("input", m_text));
                }
            });

            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                    return;
                }
            });

            alert.show();
        }
        else {
            mListener.onFuncSelected(command);
        }

//        if (null != mListener) {
//            // Notify the active callbacks interface (the activity, if the
//            // fragment is attached to one) that an item has been selected.
//            mListener.onFuncSelected(input_text);
//        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyText instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
    public interface OnFuncSelectedListener {
        public void onFuncSelected(String id);
    }

}
