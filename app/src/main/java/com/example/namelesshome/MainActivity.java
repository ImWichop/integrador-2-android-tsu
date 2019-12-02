package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

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
                Toast.makeText(this, "LEDS", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.doors:
                Toast.makeText(this, "DOORS", Toast.LENGTH_SHORT).show();
                break;
            case R.id.garage:
                Toast.makeText(this, "GARAGE", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Toast.makeText(this, "LOGOUT", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
