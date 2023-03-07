package com.feedbox.clubcommunity.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.feedbox.clubcommunity.R;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;


public class HomeFragment extends Fragment {

    TextView hm_tv_joke;
    
    Button hf_btn_click;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        hf_btn_click= view.findViewById(R.id.hf_btn_click);
        hm_tv_joke= view.findViewById(R.id.hm_tv_joke);
        hf_btn_click.setOnClickListener(view1 -> getProgrammingJoke());
        
        
        return view;
    }

    private void getProgrammingJoke() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "https://v2.jokeapi.dev/joke/Programming?type=single";

    // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,
                response ->{
            try{
                String resp = response.getString("joke");
                hm_tv_joke.setText(resp);
            }catch (Exception e){
                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }


                    Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                } ,
                error -> Toast.makeText(getContext(), error.getLocalizedMessage(), Toast.LENGTH_SHORT).show());

    // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
}