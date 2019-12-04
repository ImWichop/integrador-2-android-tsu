package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class DoorsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doors);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDoor1:
                Toast.makeText(this, "Room1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnDoor2:
                Toast.makeText(this, "Room2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnMainDoor:
                Toast.makeText(this, "DoorMain", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnGarageDoor:
                Toast.makeText(this, "Garage", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
