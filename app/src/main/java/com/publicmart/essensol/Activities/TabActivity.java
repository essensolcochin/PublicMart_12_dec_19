package com.publicmart.essensol.Activities;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.publicmart.essensol.Adapters_.MyAdapter;
import com.publicmart.essensol.R;


public class TabActivity extends BaseActivity {
TabLayout tabLayout;
ViewPager TabItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_tab, contentFrameLayout);


        TabItem= findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.bookTab);

//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.plane));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.train));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ship));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final MyAdapter adapter = new MyAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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
