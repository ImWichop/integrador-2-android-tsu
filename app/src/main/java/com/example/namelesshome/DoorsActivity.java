package com.example.namelesshome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

public class DoorsActivity extends AppCompatActivity implements View.OnClickListener {
    private VolleyS volley;
    protected RequestQueue fRequestQueue;
    SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doors);

        volley = VolleyS.getInstance(this.getApplicationContext());
        fRequestQueue = volley.getRequestQueue();
        refreshLayout = findViewById(R.id.refreshDoors);

        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/puertas.puerta1/data/last", R.id.btnMainDoor);
        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/puertas.puerta2/data/last", R.id.btnDoor1);
        checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/puertas.puerta3/data/last", R.id.btnDoor2);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/puertas.puerta1/data/last", R.id.btnMainDoor);
                checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/puertas.puerta2/data/last", R.id.btnDoor1);
                checkStatus("https://io.adafruit.com/api/v2/Castorena/feeds/puertas.puerta3/data/last", R.id.btnDoor2);
                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button b = (Button)v;
        String buttonText = b.getText().toString();
        String value = "";
        if(buttonText.equals("OPEN")){
            value = "CLOSE";
        }else {
            value = "OPEN";
        }
        switch (v.getId()){
            case R.id.btnMainDoor:
                turnOn("https://io.adafruit.com/api/v2/Castorena/groups/puertas/feeds/puertas.puerta1/data", value,b);
                changeColor(b);
                break;
            case R.id.btnDoor1:
                turnOn("https://io.adafruit.com/api/v2/Castorena/groups/puertas/feeds/puertas.puerta2/data", value,b);
                changeColor(b);
                break;
            case R.id.btnDoor2:
                turnOn("https://io.adafruit.com/api/v2/Castorena/groups/puertas/feeds/puertas.puerta3/data", value,b);
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
        if(!b.getText().equals("OPEN")){
            b.setBackgroundResource(R.drawable.button_red);
        }else {
            b.setBackgroundResource(R.drawable.button_light);
        }
    }
}
