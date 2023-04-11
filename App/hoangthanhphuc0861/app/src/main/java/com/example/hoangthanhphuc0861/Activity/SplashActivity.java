package com.example.hoangthanhphuc0861.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.hoangthanhphuc0861.Fragment.HomeFragment;
import com.example.hoangthanhphuc0861.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeFragment.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}