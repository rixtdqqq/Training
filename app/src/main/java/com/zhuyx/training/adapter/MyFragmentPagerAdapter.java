package com.zhuyx.training.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;
    private String[] mTitles;

    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> mContents, String[] titles) {
        super(fm);
        list = mContents;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        if (position < list.size()) {
            return list.get(position);
        } else {
            return list.get(0);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null && mTitles.length > 0) {
            return mTitles[position];
        }
        return super.getPageTitle(position);
    }
}
