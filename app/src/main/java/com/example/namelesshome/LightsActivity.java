package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LightsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRoom1:
                Toast.makeText(this, "Room1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRoom2:
                Toast.makeText(this, "Room2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRoom3:
                Toast.makeText(this, "Room3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRoom4:
                Toast.makeText(this, "Room4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRoom5:
                Toast.makeText(this, "Room5", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
