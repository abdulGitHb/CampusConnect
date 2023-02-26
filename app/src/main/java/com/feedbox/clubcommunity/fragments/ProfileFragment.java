package com.feedbox.clubcommunity.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.feedbox.clubcommunity.R;

public class ProfileFragment extends Fragment {
    Button pf_btn_click;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        pf_btn_click= view.findViewById(R.id.pf_btn_click);

        pf_btn_click.setOnClickListener(view1->{
            Toast.makeText(getContext(), "Profile Fragment", Toast.LENGTH_SHORT).show();
        });




        return view;
    }
}