package com.xu.xbasketball.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.di.component.ActivityComponent;
import com.xu.xbasketball.di.component.DaggerActivityComponent;
import com.xu.xbasketball.di.module.ActivityModule;
import com.xu.xbasketball.utils.SnackBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Xu on 2018/3/28.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 1;

    protected Activity mContext;
    protected View view;
    private Unbinder unbinder;

    protected RxPermissions rxPermissions;
    protected CompositeDisposable compositeDisposable;

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
        view = ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
//        requestPermission();
        viewCreated();
        initData();
    }

    protected void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
        unbinder.unbind();
        if (compositeDisposable != null) {
            compositeDisposable.clear();
        }
    }

    public abstract int getLayoutId();

    protected abstract void initInject();

    protected void requestPermission(Consumer<Boolean> consumer, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (rxPermissions == null) {
                rxPermissions = new RxPermissions(this);
            }
            if (compositeDisposable == null) {
                compositeDisposable = new CompositeDisposable();
            }
            compositeDisposable.add(rxPermissions.request(permissions).subscribe(consumer));
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                    != PackageManager.PERMISSION_GRANTED ||
//                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
//                            != PackageManager.PERMISSION_GRANTED) {
//
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                                Manifest.permission.READ_PHONE_STATE},
//                        MY_PERMISSIONS_REQUEST);
//            }

        }
    }

    public void viewCreated() {

    }

    public abstract void initData();

}
