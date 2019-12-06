package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LightsActivity extends AppCompatActivity implements View.OnClickListener {
    private VolleyS volley;
    protected RequestQueue fRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto1/data/last", R.id.btnRoom1);
        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto2/data/last", R.id.btnRoom2);
        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto3/data/last", R.id.btnRoom3);
        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto4/data/last", R.id.btnRoom4);
        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto5/data/last", R.id.btnRoom5);
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        String value = "";
        if(buttonText == "ON"){
            value = "OFF";
            b.setText("OFF");
        }else {
            value = "ON";
            b.setText("ON");
        }
        switch (v.getId()){
            case R.id.btnRoom1:
                turnOn("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto1/data", value);
                changeColor(b);
                Toast.makeText(this, "Room1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRoom2:
                turnOn("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto2/data", value);
                changeColor(b);
                Toast.makeText(this, "Room2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRoom3:
                turnOn("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto3/data", value);
                changeColor(b);
                Toast.makeText(this, "Room3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRoom4:
                turnOn("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto4/data",value);
                changeColor(b);
                Toast.makeText(this, "Room4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnRoom5:
                turnOn("https://io.adafruit.com/api/v2/Castorena/feeds/cuarto5/data",value);
                changeColor(b);
                Toast.makeText(this, "Room5", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void turnOn(String url,String value){
        JSONObject data =  new JSONObject();
        try {
            data.put("value", value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String respuesta = response.getString("value");
                    Log.d("OnTurnLED", respuesta);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params =  new HashMap<String,String>();
                params.put("X-AIO-Key","35d5cc8177b24e198574c9db7aaa5ba2");
                return params;
            }
        };

        fRequestQueue.add(request);
    }

    public void checkStatus(String url, final Integer idBtn){
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    String respuesta = response.getString("value");
                    Button b = findViewById(idBtn);
                    b.setText(respuesta);
                    Log.d("OnStatusLED", respuesta);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponseLED: ", error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params =  new HashMap<String,String>();
                params.put("X-AIO-Key","35d5cc8177b24e198574c9db7aaa5ba2");
                return params;
            }
        };

        fRequestQueue.add(request);
    }

    public void changeColor(Button b){
        if(b.getText() != "ON"){
            b.setBackgroundResource(R.drawable.button_red);
        }else {
            b.setBackgroundResource(R.drawable.button_light);
        }
    }
}
