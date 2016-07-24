package com.tech.sincon.xless.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.tech.sincon.xless.R;
import com.tech.sincon.xless.extras.ApplicationController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
                processRequest("http://41.222.176.233:8080/v0.14/MM/transactions/");
            }
        });


    }

    //push notification parameters
    public Map<String, String> getParameters() {
        Map<String, String> params = new HashMap<>();

        // the POST parameters:
        params.put("amount", "4000");
        return params;

    }



    public void processRequest(String URL) {

        String json = "{ \"amount\": 500, \"type\": \"transfer\", \"debitParty\": [ { \"key\": \"MSISDN\", \"value\": \"255718532546\" } ], \"creditParty\": [ { \"key\": \"MSISDN\", \"value\": \"255718532531\" } ] }";
        Map<String, Object> jsonParams2 = new HashMap<String, Object>();
        try {
            jsonParams2 = jsonString2Map(json);
        } catch (Exception e) {

        }
        Log.w("Check point", "Point A");
        Log.w("Data", jsonParams2.toString());
        JsonObjectRequest myRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                new JSONObject(jsonParams2),

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                      /*  verificationSuccess(response);*/
                        Log.w("Response", response.toString());
                        Log.w("CheckPoint", "Point B");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       /* verificationFailed(error);*/
                        Log.w("CheckPoint", "Point C");
                        Log.w("Error", error.toString());
                    }
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Basic MjU1NzE4NTMzMTAyOjAwMDA=");
                return headers;
            }
        };
        myRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Log.w("CheckPoint", "Point D");
        ApplicationController.getInstance().addToRequestQueue(myRequest, "Send Payment");
    }

    public static Map<String, Object> jsonString2Map( String jsonString ) throws JSONException {
        Map<String, Object> keys = new HashMap<String, Object>();

        org.json.JSONObject jsonObject = new org.json.JSONObject( jsonString ); // HashMap
        Iterator<?> keyset = jsonObject.keys(); // HM

        while (keyset.hasNext()) {
            String key =  (String) keyset.next();
            Object value = jsonObject.get(key);
            System.out.print("\n Key : "+key);
            if ( value instanceof org.json.JSONObject ) {
                System.out.println("Incomin value is of JSONObject : ");
                keys.put( key, jsonString2Map( value.toString() ));
            }else if ( value instanceof org.json.JSONArray) {
                org.json.JSONArray jsonArray = jsonObject.getJSONArray(key);
                //JSONArray jsonArray = new JSONArray(value.toString());
                keys.put( key, jsonArray2List( jsonArray ));
            } else {
//                keyNode( value);
                keys.put( key, value );
            }
        }
        return keys;
    }

    public static List<Object> jsonArray2List(JSONArray arrayOFKeys ) throws JSONException{
        System.out.println("Incoming value is of JSONArray : =========");
        List<Object> array2List = new ArrayList<Object>();
        for ( int i = 0; i < arrayOFKeys.length(); i++ )  {
            if ( arrayOFKeys.opt(i) instanceof JSONObject ) {
                Map<String, Object> subObj2Map = jsonString2Map(arrayOFKeys.opt(i).toString());
                array2List.add(subObj2Map);
            }else if ( arrayOFKeys.opt(i) instanceof JSONArray ) {
                List<Object> subarray2List = jsonArray2List((JSONArray) arrayOFKeys.opt(i));
                array2List.add(subarray2List);
            }else {
//                keyNode( arrayOFKeys.opt(i) );
                array2List.add( arrayOFKeys.opt(i) );
            }
        }
        return array2List;
    }

}

