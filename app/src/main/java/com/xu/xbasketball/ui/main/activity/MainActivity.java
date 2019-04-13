package com.xu.xbasketball.ui.main.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.base.BaseFragment;
import com.xu.xbasketball.ui.basketball.fragment.BasketballFragment;
import com.xu.xbasketball.ui.main.fragment.AboutFragment;
import com.xu.xbasketball.ui.main.fragment.SettingFragment;
import com.xu.xbasketball.ui.pic.fragment.PicFragment;
import com.xu.xbasketball.ui.video.fragment.VideoFragment;

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
    private VideoFragment videoFragment;
    private SettingFragment settingFragment;
    private AboutFragment aboutFragment;

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
        setToolBar(toolbar, "XBasketball");
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open, R.string.close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public void initData() {
        basketballFragment = new BasketballFragment();
        picFragment = new PicFragment();
        videoFragment = new VideoFragment();
        settingFragment = new SettingFragment();
        aboutFragment = new AboutFragment();

        navigation.getMenu().findItem(R.id.navigation_basketball).setChecked(true);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_pic:
                        setTargetFragment(picFragment);
                        break;
                    case R.id.navigation_video:
                        setTargetFragment(videoFragment);
                        break;
                    case R.id.navigation_basketball:
                        setTargetFragment(basketballFragment);
                        break;
                    case R.id.navigation_setting:
                        setTargetFragment(settingFragment);
                        break;
                    case R.id.navigation_about:
                        setTargetFragment(aboutFragment);
                        break;
                }
                item.setChecked(true);
                if (item.getTitle().equals(getString(R.string.drawer_main))) {
                    toolbar.setTitle("XBasketball");
                } else if (item.getTitle().equals(getString(R.string.drawer_setting))) {
                    toolbar.setTitle("系统设置");
                } else if (item.getTitle().equals(getString(R.string.drawer_about))) {
                    toolbar.setTitle("关于XBasketball");
                } else {
                    toolbar.setTitle("篮球" + item.getTitle());
                }
                mDrawerLayout.closeDrawers();
                return false;
            }
        });

        setTargetFragment(basketballFragment);

//        OkHttpClient client = new OkHttpClient();
//        final Request request = new Request.Builder()
//                .url("https://m.hupu.com/bbs/23371913.html")
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
//                Log.i("test", "success1");
//                String result = response.body().string();
//
//            }
//        });
    }

    private void setTargetFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_main_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        showExitDialog();
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("您确定要退出XBasketball吗?");
        builder.setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                App.getInstance().exitApp();
            }
        });
        builder.show();
    }
}
