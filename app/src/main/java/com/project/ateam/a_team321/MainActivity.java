package com.project.ateam.a_team321;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.project.ateam.a_team321.Fragment.AkunFragment;
import com.project.ateam.a_team321.bottomNavigation.BerandaFragment;
import com.project.ateam.a_team321.imageRecognition.AnalisisFragment;

public class MainActivity extends AppCompatActivity {
    public static final String KEY_FRAGMENT = "fragment";
    private Fragment pageContent = new BerandaFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, pageContent).commit();
        }else {

        }


        BottomNavigationView mBottomNav = findViewById(R.id.nav_bottom);

        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        pageContent = new BerandaFragment();
                        break;
                    case R.id.nav_pesan:
                        pageContent = new PesanFragment();
                        break;
                    case R.id.nav_akun:
                        pageContent = new AkunFragment();
                        break;

                    case R.id.nav_analisa:
                            pageContent = new AnalisisFragment();
                            break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, pageContent).commit();
                return true;
            }

        });


    }
}
