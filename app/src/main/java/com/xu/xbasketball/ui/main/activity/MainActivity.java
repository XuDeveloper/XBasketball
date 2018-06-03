package com.xu.xbasketball.ui.main.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.ui.main.adapter.MainFragmentPagerAdapter;

import butterknife.BindView;

/**
 * Created by Xu on 2018/3/30.
 *
 * @author Xu
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.navigation)
    NavigationView navigation;

    private MainFragmentPagerAdapter mFragmentPagerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void viewCreated() {
        super.viewCreated();
        initToolbar(toolbar);
    }

    @Override
    public void initData() {
        mFragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager(), mContext);
        viewpager.setAdapter(mFragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewpager);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    
                }
                return false;
            }
        });
//        OkHttpClient client = new OkHttpClient();
//        final Request request = new Request.Builder()
//                .url("http://tags.open.qq.com/interface/tag/articles.php?callback=tagListCb&p=1&l=20&tag=NBA&oe=gbk&ie=utf-8&source=web&site=sports&_=1527505381421")
//                .get()
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.i("test", e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.i("test", "success");
//                String result = response.body().string();
////                TencentNewsResultBean resultBean = new Gson().fromJson(result, TencentNewsResultBean.class);
//                Log.i("test", result);
//            }
//        });
    }

    private void initToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
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
