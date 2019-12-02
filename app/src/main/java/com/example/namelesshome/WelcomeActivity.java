package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLoginWelcome:
                Intent it = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(it);
            break;
            case  R.id.txtRegisterWelcome:
                Intent itt = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(itt);
                break;
        }
    }
}
