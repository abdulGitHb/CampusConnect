package com.feedbox.clubcommunity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView btn_to_login;
    Spinner identity_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_to_login= findViewById(R.id.btn_to_login);
        identity_spinner= findViewById(R.id.identity_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.identity, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        identity_spinner.setAdapter(adapter);

        identity_spinner.setOnItemSelectedListener(this);

        btn_to_login.setOnClickListener(view -> startActivity(new Intent(SignupActivity.this, LoginActivity.class)));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}
}