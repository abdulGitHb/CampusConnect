package com.feedbox.clubcommunity.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.feedbox.clubcommunity.LoginActivity;
import com.feedbox.clubcommunity.R;
import com.google.firebase.auth.FirebaseAuth;

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
            Toast.makeText(getContext(), "Logging you out..., please wait", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        });




        return view;
    }
}