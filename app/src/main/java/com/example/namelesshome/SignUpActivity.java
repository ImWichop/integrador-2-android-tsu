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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private VolleyS volley;
    protected RequestQueue fRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignup:
                signup();
                break;
            case R.id.txtLoginSignup:
                loginActivity();
                finish();
                break;
        }
    }

    public void signup(){
        String url = "http://home4.uttics.com/api/registro";
        final EditText name = findViewById(R.id.txtNameSignup);
        final EditText email = findViewById(R.id.txtEmailSignup);
        final EditText pass = findViewById(R.id.txtPasswordSignup);
        JSONObject data =  new JSONObject();
        try {
            data.put("name", name.getText());
            data.put("email", email.getText());
            data.put("password", pass.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(SignUpActivity.this, "Signed Up", Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                pass.setText("");
                loginActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        });

        fRequestQueue.add(request);
    }

    public void loginActivity(){
        Intent it = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(it);
    }
}
