package com.publicmart.essensol.Adapters_;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.publicmart.essensol.TabFragments.FoodMenuTab;
import com.publicmart.essensol.TabFragments.LoginTab;

public class LoginTabAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    private int tabCount;

    //Constructor to the class
    public LoginTabAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount = tabCount;
    }




    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                FoodMenuTab tab1 = new FoodMenuTab();
                return tab1;
            case 1:
                LoginTab tab2 = new LoginTab();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
