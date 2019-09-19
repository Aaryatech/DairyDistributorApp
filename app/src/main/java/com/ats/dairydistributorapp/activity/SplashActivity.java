package com.ats.dairydistributorapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.ats.dairydistributorapp.R;
import com.ats.dairydistributorapp.util.CustomSharedPreference;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        CustomSharedPreference.putString(SplashActivity.this, CustomSharedPreference.KEY_LANGUAGE, CustomSharedPreference.LANGUAGE_ENG_ID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            }
        }, 2500);

    }
}
