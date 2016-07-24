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

import com.tech.sincon.xless.Http.AppHttp;
import com.tech.sincon.xless.R;

import java.util.HashMap;
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
                sendRequest(amnt.getText().toString(), pin.getText().toString());
            }
        });









    }

    private  void sendRequest(String amnt, String pin){
      /*  AppHttp appHttp = new AppHttp(this);
        appHttp.postData("", getParameters(amnt, pin));*/
        Toast.makeText(this,amnt +" "+pin,Toast.LENGTH_SHORT).show();
    }

    //push notification parameters
    public Map<String, String> getParameters(String amnt, String pin) {
        Map<String, String> params = new HashMap<>();

        // the POST parameters:
        params.put("amount", amnt);
        params.put("pin", pin);
        return params;

    }
}
