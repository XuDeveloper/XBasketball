package com.xu.xbasketball.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.xu.xbasketball.app.App;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Xu on 2018/3/28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
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

    public void viewCreated() {

    }

    public abstract void initData();
}
