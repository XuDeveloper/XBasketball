package com.xu.xbasketball.ui.basketball.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Xu on 2018/6/4.
 *
 * @author Xu
 */
public class BasketballFragment extends BaseFragment {


    @BindView(R.id.tb_basketball)
    Toolbar tbBasketball;
    @BindView(R.id.tl_basketball)
    TabLayout tlBasketball;
    @BindView(R.id.vp_basketball)
    ViewPager vpBasketball;

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_basketball_main;
    }

    private void initToolbar(Toolbar toolbar) {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
//        mDrawerToggle.syncState();
    }

}
