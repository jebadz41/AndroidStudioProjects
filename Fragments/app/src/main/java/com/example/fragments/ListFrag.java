package com.example.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListFrag extends ListFragment {

    ItemSelected activity;

    public interface ItemSelected
    {
        void onItemSelection(int index);
    }

    public ListFrag() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (ItemSelected) context;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String [] data =getResources().getStringArray(R.array.pieces);

        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data));

        //the phone is in landscape mode
        if(this.getActivity().findViewById(R.id.layout_land) != null)
        {
            activity.onItemSelection(0);
        }


       // activity.onItemSelection(0);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id)
    {
        activity.onItemSelection(position);
    }
}