package com.example.mywallpapers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spalsh);

        Handler han=new Handler();
        han.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it=new Intent(splash.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        },2000);


    }
}