package com.feedbox.clubcommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView btn_to_signup;
    Button la_btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_to_signup= findViewById(R.id.btn_to_signup);
        la_btn_login= findViewById(R.id.la_btn_login);

        btn_to_signup.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
        la_btn_login.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));

    }
}