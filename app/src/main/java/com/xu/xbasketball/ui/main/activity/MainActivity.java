package com.xu.xbasketball.ui.main.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.base.BaseFragment;
import com.xu.xbasketball.ui.basketball.fragment.BasketballFragment;
import com.xu.xbasketball.ui.pic.fragment.PicFragment;

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
    @BindView(R.id.navigation)
    NavigationView navigation;

    private BasketballFragment basketballFragment;
    private PicFragment picFragment;

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
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public void initData() {
        basketballFragment = new BasketballFragment();
        picFragment = new PicFragment();
        navigation.getMenu().findItem(R.id.navigation_basketball).setChecked(true);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_pic:
                        setTargetFragment(picFragment);
                }
                return false;
            }
        });

        setTargetFragment(basketballFragment);
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
    }

    private void setTargetFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_main_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
