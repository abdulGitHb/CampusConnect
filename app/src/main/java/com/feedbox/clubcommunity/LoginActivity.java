package com.feedbox.clubcommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView btn_to_signup, heading_login, la_tv_forgot_pass;
    Button la_btn_login;
    EditText et_login_gmail, et_login_password;
    String gmail, password;
    ProgressBar la_prgress_bar;
    RequestQueue rq;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_to_signup= findViewById(R.id.btn_to_signup);
        la_btn_login= findViewById(R.id.la_btn_login);
        et_login_gmail= findViewById(R.id.et_login_gmail);
        et_login_password= findViewById(R.id.et_login_password);
        la_prgress_bar= findViewById(R.id.la_prgress_bar);
        heading_login= findViewById(R.id.heading_login);
        la_tv_forgot_pass= findViewById(R.id.la_tv_forgot_pass);



        btn_to_signup.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
        la_btn_login.setOnClickListener(view -> loginUserMongo());
        la_tv_forgot_pass.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));

    }

    private void loginUserMongo() {
        la_prgress_bar.setVisibility(View.VISIBLE);
        gmail= et_login_gmail.getText().toString();
        password= et_login_password.getText().toString();

        if(gmail.isEmpty()){
            Toast.makeText(this, "Please Enter Email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(this, "Password cannot be Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        String url= "http://192.168.1.33:8000/login";
        StringRequest sr = new StringRequest(Request.Method.POST,
                url,
                response -> heading_login.setText(response.toString()),
                error -> Toast.makeText(this, "Error Occured...", Toast.LENGTH_SHORT).show()){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("email", et_login_gmail.getText().toString());
                params.put("password", et_login_password.getText().toString());

                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        rq= Volley.newRequestQueue(this);
        rq.add(sr);
    }

}