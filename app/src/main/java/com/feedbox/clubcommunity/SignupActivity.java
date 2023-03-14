package com.feedbox.clubcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.feedbox.clubcommunity.modals.ClubMember;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class SignupActivity extends AppCompatActivity{

    TextView btn_to_login, sa_tv_select_skill,heading_signup;
    Button sa_btn_signup;
    EditText sa_et_full_name, sa_et_contact_no, sa_et_gmail, sa_et_pass;
    Spinner identity_spinner, sa_collegename_spinner;
    ProgressBar sa_progress_bar;
    String collegeName, identity;

    DatabaseReference saDbReference;
    FirebaseAuth userCreation;

    boolean[] selectedSkill;
    ArrayList<String> skillList = new ArrayList<>();

    String[] skillArray = {"Web Development","App Development","SEO","Linkedin Optimization","Graphic Design",
            "Video Editing","Time Management","Digital Marketing","Content Writing","Ads"};
    private RequestQueue rq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        Getting and Linking all the IDs
        btn_to_login= findViewById(R.id.btn_to_login);
        sa_collegename_spinner= findViewById(R.id.sa_collegename_spinner);
        sa_et_full_name= findViewById(R.id.sa_et_full_name);
        sa_et_contact_no= findViewById(R.id.sa_et_contact_no);
        sa_et_gmail= findViewById(R.id.sa_et_gmail);
        sa_et_pass= findViewById(R.id.sa_et_pass);
        sa_btn_signup= findViewById(R.id.sa_btn_signup);
        sa_progress_bar= findViewById(R.id.sa_progress_bar);
        sa_tv_select_skill= findViewById(R.id.sa_tv_select_skill);
        heading_signup= findViewById(R.id.heading_signup);

        userCreation= FirebaseAuth.getInstance();

        // initializing the selected skill array
        selectedSkill= new boolean[skillArray.length];
        // setting on-click on the text view.
        sa_tv_select_skill.setOnClickListener(view -> selectSkill());



//        selecting the college name from the dropdown list.
        ArrayAdapter<CharSequence> clgNameAdapter = ArrayAdapter.createFromResource(this, R.array.college_name, android.R.layout.simple_spinner_dropdown_item);
        clgNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sa_collegename_spinner.setAdapter(clgNameAdapter);

        sa_collegename_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                collegeName=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }});


        btn_to_login.setOnClickListener(view -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));

//        sa_btn_signup.setOnClickListener(view -> registerNewUser());

        RequestQueue rq = Volley.newRequestQueue(this);
        String url= "http://192.168.1.33:8000/register";
        rq.start();
        sa_btn_signup.setOnClickListener(view -> newPostDataJson());

    }


    // -------------   this one is working fine, sending all type of data to the backend(using it). -------------------
    private void newPostDataJson() {
        String url= "http://192.168.1.33:8000/register";

        JsonObjectRequest jsonRequest= new JsonObjectRequest(
                Request.Method.POST, url, null,
                response -> heading_signup.setText(response.toString()),
                error -> Toast.makeText(this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show()){

            @Override
            public byte[] getBody() {
                JSONObject jsonObject = new JSONObject();
                String body = null;
                String[] skillz= {"App", "Web"};
                try
                {
//                  name, email, password, phone, college,skills
                    jsonObject.put("name", sa_et_full_name.getText().toString());
                    jsonObject.put("email", sa_et_gmail.getText().toString());
                    jsonObject.put("password", sa_et_pass.getText().toString());
                    jsonObject.put("phone", sa_et_contact_no.getText().toString());
                    jsonObject.put("college", collegeName);
                    jsonObject.put("skills", new JSONArray(skillList));

                    body = jsonObject.toString();
                } catch (JSONException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                try
                {
                    return body.toString().getBytes("utf-8");
                } catch (UnsupportedEncodingException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            public Map getHeaders()
            {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(SignupActivity.this);
        requestQueue.add(jsonRequest);

    }

// -------------   this one is working fine for single type of data, sending data to the backend(but not using it). -------------------
//    private void newPostData() {
//        String url= "http://192.168.1.33:8000/register";
//        StringRequest sr = new StringRequest(Request.Method.POST,
//                url,
//                response -> {
//                    heading_signup.setText(response.toString());
//                    Toast.makeText(this, "response received", Toast.LENGTH_SHORT).show();
//                },
//                error -> Toast.makeText(this, error.getMessage(), Toast.LENGTH_LONG).show()){
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError{
////                name,email,password,phone, college, skills
//                Map<String, String> params= new HashMap<>();
//                params.put("name", "abdul");
//                params.put("email", "test1@gmail.com");
//                params.put("password", "12345");
//                params.put("collegeName", "IET-DAVV");
////                params.put("branch", "8989585856");
////                params.put("phone", sa_et_contact_no.getText().toString());
//                params.put("branch", "CS-A");
////                params.put("year", "4th year");
//
//                return params;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String,String> params = new HashMap<>();
//                params.put("Content-Type","application/x-www-form-urlencoded");
//                return params;
//            }
//        };
//        rq= Volley.newRequestQueue(this);
//        rq.add(sr);
//    }


    // method for selecting skill using multi-select spinner
    private void selectSkill() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Select Skills");
        builder.setCancelable(false);

        builder.setMultiChoiceItems(skillArray, selectedSkill, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b){
                    skillList.add(skillArray[i]);
                    Collections.sort(skillList);
                }else{
                    skillList.remove(Integer.valueOf(i));
                }
            }
        });

        // this will set the positive button on the dialog
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            sa_tv_select_skill.setText(skillList.toString());
        });

        // this will set the negative button on the dialog
        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {
           dialogInterface.dismiss();
        });

        // this will set the neutral button on the dialog
        builder.setNeutralButton("Clear All", (dialogInterface, i) -> {
            for (int j = 0; j < selectedSkill.length; j++) {
                selectedSkill[j]= false;
                skillList.clear();
                sa_tv_select_skill.setText("");
            }
        });

        builder.show();
    }

    private void registerNewUser(){
        String name= sa_et_full_name.getText().toString();
        String contact= sa_et_contact_no.getText().toString();
        String gmail= sa_et_gmail.getText().toString();
        String pass= sa_et_pass.getText().toString();
        Boolean isAdmin= false;
        Boolean isSuperAdmin= false;
        Boolean isLead= false;

        if(name.isEmpty() || contact.isEmpty()
        || gmail.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Please fill in all the details!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(collegeName=="--Select College--"){
            Toast.makeText(this, "Please select a College", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(collegeName=="--Select Identity--"){
            Toast.makeText(this, "Please select Identity", Toast.LENGTH_SHORT).show();
            return;
        }

            sa_progress_bar.setVisibility(View.VISIBLE);
            userCreation.createUserWithEmailAndPassword(gmail, pass)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Registration Successful! Logging you in", Toast.LENGTH_SHORT).show();
                            sa_progress_bar.setVisibility(View.GONE);
                            Toast.makeText(this, collegeName, Toast.LENGTH_SHORT).show();
                            addStudentCollege(name, contact, gmail, false, false, false);
                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                        }else{
                            Toast.makeText(SignupActivity.this, "Registration Failed, please try again later!", Toast.LENGTH_SHORT).show();
                            sa_progress_bar.setVisibility(View.GONE);
                        }
                    });

        }


    private void addStudentCollege(String name, String contact, String gmail, Boolean isAdmin, Boolean isSuperAdmin, Boolean isLead) {
        saDbReference = FirebaseDatabase.getInstance().getReference().child("College");
        FirebaseUser currentUser= userCreation.getCurrentUser();
        String userId= currentUser.getUid();
//        String name, contact, gmail; Boolean isAdmin, isSuperAdmin, isLead;
        ClubMember clubMember = new ClubMember(name, contact, gmail, isAdmin, isSuperAdmin, isLead);

        saDbReference.child(collegeName).child(identity).child(userId).setValue(clubMember);
    }

}