package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leds:
                Intent leds = new Intent(getApplicationContext(),LightsActivity.class);
                startActivity(leds);
                break;
            case  R.id.doors:
                Intent doors = new Intent(getApplicationContext(),DoorsActivity.class);
                startActivity(doors);
                break;
            case R.id.garage:
                Intent garage = new Intent(getApplicationContext(),AlarmActivity.class);
                startActivity(garage);
                break;
            case R.id.logout:
                logout();
                break;
        }
    }

    public void logout(){
        SharedPreferences preferences = getSharedPreferences("credentials_Nameless", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token_Nameless", "null");
        editor.apply();

        Intent it = new Intent(getApplicationContext(),WelcomeActivity.class);
        startActivity(it);
    }
}
