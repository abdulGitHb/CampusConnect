package com.feedbox.clubcommunity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.feedbox.clubcommunity.fragments.HomeFragment;
import com.feedbox.clubcommunity.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView am_bottom_nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        am_bottom_nav_view= findViewById(R.id.am_bottom_nav_view);

        am_bottom_nav_view.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment= null;
        switch (item.getItemId()){
            case R.id.bn_btn_home:
                fragment = new HomeFragment();
              break;
            case R.id.bn_btn_calendar:
                Toast.makeText(this, "Calendar clicked", Toast.LENGTH_SHORT).show();
              break;
            case R.id.bn_btn_additem:
                Toast.makeText(this, "Add Item clicked", Toast.LENGTH_SHORT).show();
              break;
            case R.id.bn_btn_resources:
                Toast.makeText(this, "Resources clicked", Toast.LENGTH_SHORT).show();
              break;
            case R.id.bn_btn_profile:
                fragment= new ProfileFragment();
              break;
              }

        if(fragment!=null){
            loadFragment(fragment);
        }

        return true;
    }

    void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.am_fragment_holder, fragment).commit();
    }
}