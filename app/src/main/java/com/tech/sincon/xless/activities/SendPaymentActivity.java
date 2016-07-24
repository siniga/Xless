package com.tech.sincon.xless.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tech.sincon.xless.Http.AppHttp;
import com.tech.sincon.xless.R;
import com.tech.sincon.xless.api.EndpointApi;
import com.tech.sincon.xless.extras.ApplicationController;
import com.tech.sincon.xless.models.Transaction;

import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendPaymentActivity extends AppCompatActivity {
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_payment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get feed id from previous activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = extras.getString("NAME");
        }


        TextView username = (TextView) findViewById(R.id.username);
        final TextView amnt = (TextView) findViewById(R.id.amnt);
        final TextView pin = (TextView) findViewById(R.id.pin);
        Button btn = (Button) findViewById(R.id.sendBtn);

        username.setText(name);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParament("http://41.222.176.233:8080/v0.14/MM/transactions");
            }
        });


    }

    private  void sendRequest(String amnt, String pin){

        /*AppHttp appHttp = new AppHttp(this);
        appHttp.postData("http://41.222.176.233:8080/v0.14/MM/accounts/msisdn/255718532531/balance", getParameters(amnt, pin));*/
       /* Toast.makeText(this,amnt +" "+pin,Toast.LENGTH_SHORT).show();*/


    }

    //push notification parameters
    public Map<String, String> getParameters() {
        Map<String, String> params = new HashMap<>();

        // the POST parameters:
        params.put("amount", "4000");
        return params;

    }

    public void getParament(String URL) {

        Map<String, String> jsonParams = new HashMap<String, String>();
        jsonParams.put("amount", "500");
        jsonParams.put("type", "transfer");
        jsonParams.put("type", "transfer");

        JsonObjectRequest myRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                new JSONObject(jsonParams),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                      /*  verificationSuccess(response);*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       /* verificationFailed(error);*/
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                headers.put("uthorization", "Basic MjU1NzE4NTMyNTMxOjAwMDA=");
                return headers;
            }
        };
        ApplicationController.getInstance().addToRequestQueue(myRequest, "tag");
    }

}

