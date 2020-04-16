package com.lavishly.android.SubsidiaryActivities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lavishly.android.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentOneFancy.OnFragmentInteractionListenerFancy} interface
 * to handle interaction events.
 */


public class FragmentOneFancy extends Fragment {

    private OnFragmentInteractionListenerFancy mListener;



    public static FragmentOneFancy newInstance() {

        FragmentOneFancy fragmentOneFancy = new FragmentOneFancy();
        return fragmentOneFancy;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.activity_fancy, container, false);
        return view;
    }



    public void onButtonPressedFancy(Uri uriFancy) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uriFancy);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListenerFancy) {
            mListener = (OnFragmentInteractionListenerFancy) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListenerFancy");
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
    public interface OnFragmentInteractionListenerFancy {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uriFancy);
    }
}
