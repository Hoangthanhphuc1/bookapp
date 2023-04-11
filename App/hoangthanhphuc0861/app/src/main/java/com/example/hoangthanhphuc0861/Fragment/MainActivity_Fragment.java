package com.example.hoangthanhphuc0861.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.hoangthanhphuc0861.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity_Fragment extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    Button btnA, btnB;
    FrameLayout mainframelayout;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    private static final int fragment_Home = 1;
    private static final int fragment_List = 2;
    private static final int fragment_Search = 3;
    private static final int fragment_Account = 4;
    private static final int fragment_More = 5;

    private int currentFragment = fragment_Home;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);

        anhxa();
        setClick_Bottom_Nav();


        OpenFragment(new HomeFragment());
        bottom_navigation.getMenu().findItem(R.id.btnhome).setChecked(true);

    }

    private void anhxa() {
        bottom_navigation = findViewById(R.id.bottom_navigation);
    }

    private void setClick_Bottom_Nav() {
        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.btnhome:
                        openHomeFragment();
                        bottom_navigation.getMenu().findItem(R.id.btnhome).setChecked(true);
                        break;
                    case R.id.btndanhmuc:
                        openListFragment();
                        bottom_navigation.getMenu().findItem(R.id.btndanhmuc).setChecked(true);
                        break;
                    case R.id.btnsearch:
                        openSearchFragment();
                        bottom_navigation.getMenu().findItem(R.id.btnsearch).setChecked(true);
                        break;
                    case R.id.btnaccount:
                        openAccountFragment();
                        bottom_navigation.getMenu().findItem(R.id.btnaccount).setChecked(true);
                        break;
                }
                return true;
            }

            private void openAccountFragment() {
                if (currentFragment != R.id.btnaccount) {
                    OpenFragment(new AccountFragment());
                    currentFragment = fragment_Account;
                }
            }

            private void openSearchFragment() {
                if (currentFragment != R.id.btnsearch) {
                    OpenFragment(new SearchFragment());
                    currentFragment = fragment_Search;
                }
            }

            private void openListFragment() {
                if (currentFragment != R.id.btndanhmuc) {
                    OpenFragment(new ListFragment());
                    currentFragment = fragment_List;
                }
            }

            private void openHomeFragment() {
                if (currentFragment != R.id.btnhome) {
                    OpenFragment(new HomeFragment());
                    currentFragment = fragment_Home;
                }
            }
        });
    }

    private void OpenFragment(Fragment f) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframelayout,f);
        fragmentTransaction.commit();
    }

}

