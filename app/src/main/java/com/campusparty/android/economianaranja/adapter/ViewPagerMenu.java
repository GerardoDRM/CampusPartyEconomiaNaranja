package com.campusparty.android.economianaranja.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.campusparty.android.economianaranja.MapFragment;
import com.campusparty.android.economianaranja.SearchFragment;
import com.campusparty.android.economianaranja.SolutionsFragment;
import com.campusparty.android.economianaranja.SupportFragment;


public class ViewPagerMenu extends FragmentStatePagerAdapter {

    CharSequence mTitles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int mNumbOfTabsumb; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created

    public ViewPagerMenu(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.mTitles = mTitles;
        this.mNumbOfTabsumb = mNumbOfTabsumb;
    }

    /**
     * This method gets correct fragment
     * Tab View with multiple fragments
     * @param position
     * @return Fragment
     */
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SearchFragment();
            case 1:
                return new SupportFragment();
            case 2:
                return new SolutionsFragment();
            case 3:
                return new MapFragment();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        // Show  total pages.
        return mNumbOfTabsumb;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}