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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
    ArrayList<Integer> skillList = new ArrayList<>();
    String[] skillArray = {"Web Development","App Development","SEO","Linkedin Optimization","Graphic Design",
            "Video Editing","Time Management","Digital Marketing","Content Writing","Ads"};


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
        String url= "http://192.168.29.82:8000/register";
        rq.start();
        sa_btn_signup.setOnClickListener(view -> {
//            name, email, password, college, branch, year, phone, skills

        });

    }

    private void postData() {
// name, email, password, college, branch, year, phone, String[] skills
        String name= sa_et_full_name.getText().toString();
        String contact= sa_et_contact_no.getText().toString();
        String gmail= sa_et_gmail.getText().toString();
        String pass= sa_et_pass.getText().toString();
        RequestQueue rq;

        String url = "http://192.168.29.82:8000/register";


         JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(
                 Request.Method.POST, url, null,
                response -> {
                    try {
                        JSONObject jsonObject= new JSONObject((Map) response);

                        String testName = jsonObject.getString("name");
                        String testEmail = jsonObject.getString("email");
                        String testPass = jsonObject.getString("password");

                        heading_signup.setText(" email:"+testEmail+" pass:"+testPass);

                    } catch (JSONException e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                },
                error -> Toast.makeText(this, "Error: "+error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params= new HashMap<>();
                String[] skilll= {"photoshop", "Illustrator", "etc"};
//                name, email, password, phone, college,skills
                params.put("name", "name");
                params.put("email", "gmail");
                params.put("password", "pass");
                params.put("phone", "contact");


                return params;
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
        requestQueue.add(jsonObjectRequest);


    }

    // method for selecting skill using multi-select spinner
    private void selectSkill() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
        builder.setTitle("Select Skills");
        builder.setCancelable(false);

        builder.setMultiChoiceItems(skillArray, selectedSkill, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                if(b){
                    skillList.add(i);
                    Collections.sort(skillList);
                }else{
                    skillList.remove(Integer.valueOf(i));
                }
            }
        });

        // this will set the positive button on the dialog
        builder.setPositiveButton("OK", (dialogInterface, i) -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < skillList.size(); j++) {
                stringBuilder.append(skillArray[skillList.get(j)]);

                if(j != skillList.size()-1){
                    stringBuilder.append(",");
                }
            }
            sa_tv_select_skill.setText(stringBuilder.toString());
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