package com.lavishly.android.SubsidiaryActivities;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lavishly.android.R;
import com.lavishly.android.SubsidiaryActivities.ActivityAdapter.RecyclerAdapterPersonage;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentTwoPersonage.OnFragmentInteractionListenerPersonage} interface
 * to handle interaction events.
 */
public class FragmentTwoPersonage extends Fragment {

    private OnFragmentInteractionListenerPersonage mListener;
    private ListView personageListView;
    public static SharedPreferences sharedPersonagePreferences;
    public static String selectedPersonageItem;

    public static FragmentTwoPersonage newInstance() {
        return new FragmentTwoPersonage();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.activity_personage, container, false);
        RecyclerView personageRecyclerView = (RecyclerView) view.findViewById(R.id.rvPersonage);
        personageListView = (ListView) view.findViewById(R.id.lvPersonage);

        RecyclerAdapterPersonage recyclerAdapterPersonage = new RecyclerAdapterPersonage(getContext(),titlePersonage,descriptionPersoange,);
       return view;
    }



    public void onButtonPressedPersonage(Uri uriPersonage) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uriPersonage);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListenerPersonage) {
            mListener = (OnFragmentInteractionListenerPersonage) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListenerPersonage");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupPersonageUIViews();
        setupListViewPersonage();


    }
    private void setupPersonageUIViews() {

        sharedPersonagePreferences =  getSharedPreferences("PersonageItem", MODE_PRIVATE);
    }

    private void setupListViewPersonage() {
        String[] titlePersonage = getResources().getStringArray(R.array.TitleArrayPersonage);
        String[] descriptionPersonage = getResources().getStringArray(R.array.DescriptionArrayPersonage);




        personageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> personageParent, View view, int personagePositionItemClick, long id) {
                switch (personagePositionItemClick){
                    case 0:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Powerful").apply();
                        break;
                    case 1:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Successful").apply();
                        break;
                    case 2:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Rich").apply();
                        break;
                    case 3:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Leader").apply();
                        break;
                    case 4:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Valuable").apply();
                        break;
                    case 5:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Charismatic").apply();
                        break;
                    case 6:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Wise").apply();
                        break;
                    case 7:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Impressive").apply();
                        break;
                    case 8:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Fortunate").apply();
                        break;
                    case 9:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Fulfilled").apply();
                        break;
                    case 10:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Healthy").apply();
                        break;
                    case 11:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Optimistic").apply();
                        break;
                    case 12:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Blessed").apply();
                        break;
                    case 13:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Grateful").apply();
                        break;
                    case 14:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Generous").apply();
                        break;
                    case 15:
                        sharedPersonagePreferences.edit().putString(selectedPersonageItem, "Peaceful").apply();
                        break;


                }


            }
        });


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
    public interface OnFragmentInteractionListenerPersonage {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uriPersonage);
    }
}
