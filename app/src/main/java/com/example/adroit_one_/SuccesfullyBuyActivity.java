package com.example.adroit_one_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class SuccesfullyBuyActivity extends AppCompatActivity {

    private TextView tvContinueId;
    private static int SPLASH_SCREEN_TIME_OUT=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesfully_buy);

        tvContinueId = findViewById(R.id.tvContinueId);

        tvContinueId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccesfullyBuyActivity.this,NavigationDrowerUsingForFragmentsActivity.class);
                startActivity(intent);
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_succesfully_buy);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SuccesfullyBuyActivity.this, NavigationDrowerUsingForFragmentsActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}