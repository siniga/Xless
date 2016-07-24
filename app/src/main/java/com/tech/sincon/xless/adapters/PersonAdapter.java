package com.tech.sincon.xless.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tech.sincon.xless.R;
import com.tech.sincon.xless.activities.SendPaymentActivity;
import com.tech.sincon.xless.models.Person;
import com.tech.sincon.xless.tabs.PaymentTab;

import java.util.Collections;
import java.util.List;

/**
 * Created by getcore03 on 2/2/2016.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    List<Person> persons = Collections.emptyList();
    private LayoutInflater inflator;
    Context c;

    // Provide a suitable constructor (depends on the kind of dataset)
    public PersonAdapter(Context c, List<Person> persons) {
        this.persons = persons;
        this.inflator = LayoutInflater.from(c);
        this.c = c;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = inflator.inflate(R.layout.card_persons, parent, false);
        // set the view's size, margins, padding and layout parameters

        ViewHolder vh = new ViewHolder(c, v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //get a position of a current saleItem
        final Person currentPerson = persons.get(position);

        holder.mImg.setBackgroundResource(currentPerson.getImage());
        holder.mName.setText(currentPerson.getName());
        holder.mWrapper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, SendPaymentActivity.class);
                intent.putExtra("NAME", currentPerson.getName());
                c.startActivity(intent);
            }
        });

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public  TextView mName;
        public ImageView mImg;
        public LinearLayout mWrapper;
        public ViewHolder(Context context, View view) {
            super(view);

            mWrapper = (LinearLayout) view.findViewById(R.id.person_wrapper);
            mImg = (ImageView) view.findViewById(R.id.img);
            mName = (TextView) view.findViewById(R.id.name);


        }

    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return persons.size();
    }
}