package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.namelesshome.Volley.VolleyS;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private VolleyS volley;
    protected RequestQueue fRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
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
        String url = "http://home4.uttics.com/api/logout";
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> params =  new HashMap<String,String>();
                SharedPreferences preferences = getSharedPreferences("credentials_Nameless", Context.MODE_PRIVATE);
                String token = preferences.getString("token_Nameless","null");
                params.put("Authorization","Bearer " + token);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("token_Nameless", "null");
                editor.apply();
                Intent it = new Intent(getApplicationContext(),WelcomeActivity.class);
                startActivity(it);
                return params;
            }
        };

        fRequestQueue.add(request);
    }
}
