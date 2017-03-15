package com.example.todayinhistory.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.todayinhistory.MainActivity;
import com.example.todayinhistory.R;
import com.example.todayinhistory.utils.WeakHandler;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new WeakHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        }, 1500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }
}
