package com.tech.sincon.xless.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.sincon.xless.R;

/**
 * Created by getcore03 on 7/23/2016.
 */
public class DialogReceiverDetails extends DialogFragment implements View.OnClickListener{
    TextView fullname;
    Button sendBtn, cancelBtn;
    Communicator communicator;

  /*  @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        communicator = (Communicator) activity;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_receiver_detail, null);

        sendBtn = (Button) view.findViewById(R.id.sendBtn);

        cancelBtn = (Button) view.findViewById(R.id.cancelBtn);
        sendBtn.setOnClickListener(this);
        cancelBtn.setOnClickListener(this);

        setCancelable(false);

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sendBtn){
             communicator.OnDialogMessage("solo");
        }else {
            dismiss();
        }
    }

    public interface Communicator{
        public void OnDialogMessage(String message);
    }
}
