package com.e.moonchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnv;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private MyFrag frag1;
    private RoomFrag frag2;
    private MoreFrag frag3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bnv = findViewById(R.id.bottom_navi);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.button_My :
                        setFrag(0);
                        break;
                    case R.id.button_rooms :
                        setFrag(1);
                        break;
                    case R.id.button_more :
                        setFrag(2);
                        break;
                }
                return true;
            }
        });
        frag1 = new MyFrag();
        frag2 = new RoomFrag();
        frag3 = new MoreFrag();
        setFrag(0);

    }

    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch(n) {
            case 0 :
                ft.replace(R.id.main_frame, frag1);
                ft.commit();
                break;
            case 1 :
                ft.replace(R.id.main_frame, frag2);
                ft.commit();
                break;
            case 2 :
                ft.replace(R.id.main_frame, frag3);
                ft.commit();
                break;
        }
    }
}
