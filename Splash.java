package com.example.ui_kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
              Intent intent = new Intent(Splash.this,MainActivity.class);
              startActivity(intent);
              finish();
           }
        },3000);

    }
}
