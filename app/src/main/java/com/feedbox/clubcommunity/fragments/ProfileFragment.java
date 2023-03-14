package com.feedbox.clubcommunity.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.feedbox.clubcommunity.LoginActivity;
import com.feedbox.clubcommunity.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {
    ImageView pf_iv_profile_image;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        pf_iv_profile_image= view.findViewById(R.id.pf_iv_profile_image);

        pf_iv_profile_image.bringToFront();
        pf_iv_profile_image.invalidate();




        return view;
    }
}