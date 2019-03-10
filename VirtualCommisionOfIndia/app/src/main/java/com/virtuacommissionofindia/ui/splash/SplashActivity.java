package com.virtuacommissionofindia.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.virtuacommissionofindia.R;
import com.virtuacommissionofindia.base.BaseActivity;
import com.virtuacommissionofindia.ui.login.LoginActivity;


public class SplashActivity extends BaseActivity {

    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showSplashScreen();
    }

    private void showSplashScreen() {
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    @Override
    protected int getResourceId() {
        return R.layout.activity_splash;
    }
}
