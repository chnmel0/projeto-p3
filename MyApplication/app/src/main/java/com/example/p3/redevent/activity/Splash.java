package com.example.p3.redevent.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.p3.redevent.R;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent it = new Intent(Splash.this, MainActivity.class);
                    startActivity(it);
                }
            }
        };
        timer.start();
        }
        @Override
    protected void onPause(){
            super.onPause();
            finish();
        }
    };
