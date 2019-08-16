package com.publicmart.android.Adapters_;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.publicmart.android.TabFragments.BoatTab;
import com.publicmart.android.TabFragments.FlightTab;
import com.publicmart.android.TabFragments.TrainTab;

public class MyAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public MyAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FlightTab tab1 = new FlightTab();
                return tab1;
            case 1:
                TrainTab tab2 = new TrainTab();
                return tab2;
            case 2:
                BoatTab tab3 = new BoatTab();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}