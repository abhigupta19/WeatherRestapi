package com.sar.user.london;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     textView1=findViewById(R.id.textView);
         textView2=findViewById(R.id.textView2);
         textView3=findViewById(R.id.textView3);
        findweather();
    }
    private void findweather()
    {
        String url="https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae220";
        JsonObjectRequest jar=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject=response.getJSONObject("main");
                    JSONArray jsonArray=response.getJSONArray("weather");
                    JSONObject jsonObject1=jsonArray.getJSONObject(0);
                    String temp=String.valueOf(jsonObject.getDouble("temp"));
                    String des=String.valueOf(jsonObject1.getString("description"));
                   // JSONObject jsonObject2=response.getJSONObject("name");
                   // String city=String.valueOf(jsonObject2.toString());
                    double d=Double.parseDouble(temp);
                    double dl=(d-32)/1.8;
                    dl=Math.round(dl);
                    int i=(int)dl;
                    textView1.setText(String.valueOf(i));
                    // textView3.setText(city);
                    textView2.setText(des);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue =Volley.newRequestQueue(this);
        requestQueue.add(jar);
    }
}
