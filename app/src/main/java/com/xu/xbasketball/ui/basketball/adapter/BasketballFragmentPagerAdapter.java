package com.xu.xbasketball.ui.basketball.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.xu.xbasketball.R;
import com.xu.xbasketball.ui.main.FragmentFactory;

/**
 * Created by Xu on 2018/4/6.
 *
 * @author Xu
 */
public class BasketballFragmentPagerAdapter extends FragmentPagerAdapter {

    private static final int COUNT = 3;

    private String[] titles;

    public BasketballFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        titles = context.getResources().getStringArray(R.array.tab_title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return COUNT;
    }
}
