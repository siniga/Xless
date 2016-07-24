package com.tech.sincon.xless.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.tech.sincon.xless.R;
import com.tech.sincon.xless.adapters.ViewPagerAdapter;
import com.tech.sincon.xless.tabs.HistoryTab;
import com.tech.sincon.xless.tabs.HomeTab;
import com.tech.sincon.xless.tabs.PaymentTab;
import com.tech.sincon.xless.tabs.WidthdrawTab;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //set up a tab view pager
        viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        HomeTab home = new HomeTab();
        PaymentTab payment  = new PaymentTab();
        WidthdrawTab widthdraw =new  WidthdrawTab();
        HistoryTab history = new HistoryTab();

        adapter.addFrag(home, "");
        adapter.addFrag(payment, "");
        adapter.addFrag(widthdraw, "");
        adapter.addFrag(history, "");

        viewPager.setAdapter(adapter);
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_payment);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_withdraw);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_history);
    }

}
