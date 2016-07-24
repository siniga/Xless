package com.tech.sincon.xless.Http;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tech.sincon.xless.extras.ApplicationController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;

/**
 * Created by getcore03 on 7/20/2016.
 */
public class AppHttp {

    private Context c;
    private String loggedIdD, receiverID, message;

    public AppHttp(Context c) {
        this.c = c;
    }


    public void getData(final VolleyCallback callback, String url) {

        JsonArrayRequest jsonRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // the response is already constructed as a JSONObject!
                        callback.onSuccess(response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        /*check if activity is not null when user change tabs
                        and retrieve data from server*/
                    /*    ErrorManager.activityIsNull(error, c);*/
                    }
                });

        ApplicationController.getInstance().addToRequestQueue(jsonRequest);
    }


    public interface VolleyCallback {
        void onSuccess(JSONArray result);
    }


    //add forum to db
    public void postData(String url, final Map<String, String> params) {
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject result = jsonArray.getJSONObject(0);
                            Toast.makeText(c, "works up to here" + result.getString("date"), LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        /*check if activity is not null when user change tabs
                        and retrieve data from server*/
                      /*  ErrorManager.activityIsNull(error, c);*/
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {

                return params;
            }
        };
        Volley.newRequestQueue(c).add(postRequest);

    }

}
