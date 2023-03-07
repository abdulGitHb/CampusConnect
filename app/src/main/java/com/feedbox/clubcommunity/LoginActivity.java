package com.feedbox.clubcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextView btn_to_signup;
    Button la_btn_login;
    EditText et_login_gmail, et_login_password;
    String gmail, password;
    FirebaseAuth userAuthentication;
    ProgressBar la_prgress_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_to_signup= findViewById(R.id.btn_to_signup);
        la_btn_login= findViewById(R.id.la_btn_login);
        et_login_gmail= findViewById(R.id.et_login_gmail);
        et_login_password= findViewById(R.id.et_login_password);
        la_prgress_bar= findViewById(R.id.la_prgress_bar);


        userAuthentication= FirebaseAuth.getInstance();

        btn_to_signup.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, SignupActivity.class)));
        la_btn_login.setOnClickListener(view -> loginUser());

    }

    private void loginUser() {
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
        userAuthentication.signInWithEmailAndPassword(gmail, password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        la_prgress_bar.setVisibility(View.GONE);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else{
                        Toast.makeText(this, "Login Failed! PLease try again..", Toast.LENGTH_SHORT).show();
                        la_prgress_bar.setVisibility(View.GONE);
                    }
                });
    }
}