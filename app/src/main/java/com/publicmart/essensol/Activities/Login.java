package com.publicmart.essensol.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.publicmart.essensol.Adapters_.LoginTabAdapter;
import com.publicmart.essensol.R;

public class Login extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager TabItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        TabItem= findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.loginTab);

        tabLayout.addTab(tabLayout.newTab().setText("Log In"));
        tabLayout.addTab(tabLayout.newTab().setText("Food Items"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final LoginTabAdapter adapter = new LoginTabAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        TabItem.setAdapter(adapter);

        TabItem.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                TabItem.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
}
