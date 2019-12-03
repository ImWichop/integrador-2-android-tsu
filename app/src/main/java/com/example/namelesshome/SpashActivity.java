package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class SpashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPreferences();
            }
        }, 2000);
    }

    public void loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("credentials_Nameless", Context.MODE_PRIVATE);
        String token = preferences.getString("token_Nameless","null");

        if(!token.equals("null")){
            Intent it =  new Intent(getApplicationContext(),MainActivity.class);
            startActivity(it);
            finish();
        }else{
            Intent it =  new Intent(getApplicationContext(),WelcomeActivity.class);
            startActivity(it);
            finish();
        }
    }
}
