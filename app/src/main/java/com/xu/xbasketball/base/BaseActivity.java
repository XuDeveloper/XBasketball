package com.xu.xbasketball.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.di.component.ActivityComponent;
import com.xu.xbasketball.di.component.DaggerActivityComponent;
import com.xu.xbasketball.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Xu on 2018/3/28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;
    private Unbinder unbinder;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initInject();
        unbinder = ButterKnife.bind(this);
        App.getInstance().addActivity(this);
        mContext = this;
        viewCreated();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        unbinder.unbind();
    }

    public abstract int getLayoutId();

    protected abstract void initInject();

    public void viewCreated() {

    }

    public abstract void initData();

}
