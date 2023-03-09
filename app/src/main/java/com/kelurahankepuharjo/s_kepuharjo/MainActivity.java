package com.kelurahankepuharjo.s_kepuharjo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kelurahankepuharjo.s_kepuharjo.fragment.HomeFragment;
import com.kelurahankepuharjo.s_kepuharjo.fragment.PengajuanFragment;
import com.kelurahankepuharjo.s_kepuharjo.fragment.ProfileFragment;
import com.kelurahankepuharjo.s_kepuharjo.fragment.StatusFragment;

public class MainActivity extends AppCompatActivity {
    private Fragment fragmentHome = new HomeFragment();
    private Fragment fragmentPengajuan = new PengajuanFragment();
    private Fragment fragmentStatus = new StatusFragment();
    private Fragment fragmentProfil = new ProfileFragment();
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private Fragment active = fragmentHome;

    private Menu menu;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNav();
}
    private void BottomNav(){
        fragmentManager.beginTransaction().add(R.id.nav_fragment, fragmentHome).show(fragmentHome).commit();
        fragmentManager.beginTransaction().add(R.id.nav_fragment, fragmentPengajuan).hide(fragmentPengajuan).commit();
        fragmentManager.beginTransaction().add(R.id.nav_fragment, fragmentStatus).hide(fragmentStatus).commit();
        fragmentManager.beginTransaction().add(R.id.nav_fragment, fragmentProfil).hide(fragmentProfil).commit();

        bottomNavigationView = findViewById(R.id.nav_view);
        menu = bottomNavigationView.getMenu();
        menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        callFragment(0, fragmentHome);
                        break;
                    case R.id.navigation_pengajuan:
                        callFragment(1, fragmentPengajuan);
                        break;
                    case R.id.navigation_status:
//                        if (s.getStatusLogin()) {
//                            callFragment(2, fragmentAkun);
//                        } else {
////                            startActivity(new Intent(MainActivity.this, MasukActivity.class));
//                        }
                        callFragment(2, fragmentStatus);
                        break;
                    case R.id.navigation_profile:
                        callFragment(3, fragmentProfil);
                        break;
                }
                return false;
            }
        });
    }

    private void callFragment(int position, Fragment fragment) {
        menuItem = menu.getItem(position);
        menuItem.setChecked(true);
        fragmentManager.beginTransaction().hide(active).show(fragment).commit();
        active = fragment;
    }
}
