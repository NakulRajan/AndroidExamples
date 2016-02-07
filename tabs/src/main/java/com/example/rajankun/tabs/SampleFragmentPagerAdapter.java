package com.example.rajankun.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * This is a adapter which displays different fragments in different tabs.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    private final Integer PAGE_COUNT = 2;
    private CharSequence[] pageTiles = {"TAB1", "TAB2"};

    public SampleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new FirstFragment();
        }else{
            return new SecondFragment();
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return pageTiles[position];
    }

}
