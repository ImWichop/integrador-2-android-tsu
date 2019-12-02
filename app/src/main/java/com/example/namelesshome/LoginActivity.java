package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.namelesshome.Volley.VolleyS;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private VolleyS volley;
    protected RequestQueue fRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                login();
                break;
            case R.id.txtRegisterLogin:
                Intent itt = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(itt);
                finish();
                break;
        }
    }

    public void login(){
        String url = "http://home4.uttics.com/api/login";
        final EditText email = findViewById(R.id.txtEmailLogin);
        final EditText pass = findViewById(R.id.txtPasswordLogin);
        JSONObject data =  new JSONObject();
        try {
            data.put("email", email.getText());
            data.put("password", pass.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String token = response.getString("token");
                }catch (JSONException e){
                    e.printStackTrace();
                }
                Toast.makeText(LoginActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
                email.setText("");
                pass.setText("");
                mainActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        });

        fRequestQueue.add(request);
    }

    public void mainActivity(){
        Intent it = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(it);
    }
}
