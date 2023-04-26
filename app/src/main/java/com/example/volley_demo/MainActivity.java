package com.example.volley_demo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        volleyObjectResponse();
        volleyArrayResponse();
    }
    private void volleyObjectResponse(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest roq = new JsonObjectRequest(Request.Method.POST,"https://jsonplaceholder.typicode.com/todos/13", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.d("MyTag", "onResponse: "+ response.getString("title"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("MyTag", "onResponse: "+ error.toString());

            }
        });
        requestQueue.add(roq);

    }
    private void volleyArrayResponse(){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest roq = new JsonArrayRequest("https://jsonplaceholder.typicode.com/todos", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Log.d("MyTag", "onResponse: "+ jsonObject.getString("title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(roq);
    }
}