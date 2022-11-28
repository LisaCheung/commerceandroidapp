package com.example.ecommerceapp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter1 extends FragmentPagerAdapter {
    private int numTabs;

    public PagerAdapter1(@NonNull FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0 ){
            return new ItemsFragment();
        }
        else if(position == 1){
            return new ServicesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Items";
        else if (position == 1)
            title = "Services";
        return title;
    }
}
