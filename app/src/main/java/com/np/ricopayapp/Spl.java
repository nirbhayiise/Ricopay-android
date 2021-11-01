package com.np.ricopayapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Spl extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.spl);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                startActivity(new Intent(Spl.this,LoginActivity.class));
                finish();

            }
        }, 300);
    }
}

