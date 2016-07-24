package com.tech.sincon.xless.tabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.sincon.xless.R;
import com.tech.sincon.xless.adapters.PersonAdapter;
import com.tech.sincon.xless.extras.RecyclerItemClickListener;
import com.tech.sincon.xless.fragments.DialogReceiverDetails;
import com.tech.sincon.xless.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by getcore03 on 7/23/2016.
 */
public class PaymentTab extends Fragment{

    private View view;
    private FragmentActivity c;
    private List<Person> persons;
    private PersonAdapter adapter;
    private Spinner categoryTxt;
    private RecyclerView list;
    private LinearLayoutManager layoutManager;
    private TextView fullname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_payment, container, false);
        c = getActivity();

        list = (RecyclerView) view.findViewById(R.id.list);
        list.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false);
        list.setLayoutManager(layoutManager);

    /*    list.addOnItemTouchListener(
                new RecyclerItemClickListener(c, list, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {


                        // do whatever
                        FragmentManager fragmentManager = getFragmentManager();
                        DialogReceiverDetails receiverDetails = new DialogReceiverDetails();
                        receiverDetails.show(fragmentManager,"dialog");
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
*/

        categoryTxt = (Spinner) view.findViewById(R.id.category_spinner);
        initCategorySpinner();

        categoryTxt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    adapter = new PersonAdapter(c, setPersonList());
                    list.setAdapter(adapter);
                } else {
                    adapter = new PersonAdapter(c, setBusinessList());
                    list.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;

    }

    public List<Person> setPersonList() {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Khamis peter", R.drawable.ic_profile));
        persons.add(new Person("James Makua", R.drawable.ic_profile));
        persons.add(new Person("Fabian Tulip", R.drawable.ic_profile));
        persons.add(new Person("Majani Makalio", R.drawable.ic_profile));
        persons.add(new Person("Moss Deff", R.drawable.ic_profile));
        persons.add(new Person("Jirani Mwema", R.drawable.ic_profile));
        persons.add(new Person("Kelo Kalisa", R.drawable.ic_profile));
        persons.add(new Person("Janet Mutoro", R.drawable.ic_profile));
        persons.add(new Person("Meister Dila", R.drawable.ic_profile));
        persons.add(new Person("Kiana Malali", R.drawable.ic_profile));
        persons.add(new Person("Sifu Master", R.drawable.ic_profile));
        persons.add(new Person("Jeb Jehana", R.drawable.ic_profile));
        persons.add(new Person("Local Lili", R.drawable.ic_profile));


        return persons;
    }


    public List<Person> setBusinessList() {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Luku", R.drawable.ic_profile));
        persons.add(new Person("Dawasco", R.drawable.ic_profile));
        persons.add(new Person("Tango Tv", R.drawable.ic_profile));
        persons.add(new Person("Dstv", R.drawable.ic_profile));
        persons.add(new Person("Azam Tv", R.drawable.ic_profile));
        persons.add(new Person("Raha", R.drawable.ic_profile));
        persons.add(new Person("Zoku", R.drawable.ic_profile));
        persons.add(new Person("", R.drawable.ic_profile));


        return persons;
    }

    private void initCategorySpinner() {
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Personal");
        categories.add("Business");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        categoryTxt.setAdapter(dataAdapter);
    }

}
