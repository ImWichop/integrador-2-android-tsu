package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {
    private VolleyS volley;
    protected RequestQueue fRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();

        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/alarma/data/last",R.id.btnAlarm1);

    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        String value = "";
        if(buttonText.equals("ACTIVE")){
            value = "INACTIVE";
        }else {
            value = "ACTIVE";
        }
        switch (v.getId()){
            case R.id.btnAlarm1:
                turnOn("https://io.adafruit.com/api/v2/Castorena/feeds/alarma/data", value,b);
                changeColor(b);
                break;
        }
    }

    public void turnOn(String url, String value, final Button b){
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
                    b.setText(respuesta);
                    changeColor(b);
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
                    changeColor(b);
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
        if(!b.getText().equals("ACTIVE")){
            b.setBackgroundResource(R.drawable.button_red);
        }else {
            b.setBackgroundResource(R.drawable.button_light);
        }
    }
}
