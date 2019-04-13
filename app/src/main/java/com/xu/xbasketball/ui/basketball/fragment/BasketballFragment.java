package com.xu.xbasketball.ui.basketball.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseFragment;
import com.xu.xbasketball.ui.basketball.adapter.BasketballFragmentPagerAdapter;

import butterknife.BindView;

/**
 * Created by Xu on 2018/6/4.
 *
 * @author Xu
 */
public class BasketballFragment extends BaseFragment {

    @BindView(R.id.tl_basketball)
    TabLayout tlBasketball;
    @BindView(R.id.vp_basketball)
    ViewPager vpBasketball;

    private BasketballFragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    public void initData() {
        if (mFragmentPagerAdapter == null) {
            mFragmentPagerAdapter = new BasketballFragmentPagerAdapter(
                    getChildFragmentManager(), mContext);
        }
        vpBasketball.setAdapter(mFragmentPagerAdapter);
        tlBasketball.setupWithViewPager(vpBasketball);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_basketball_main;
    }

}
