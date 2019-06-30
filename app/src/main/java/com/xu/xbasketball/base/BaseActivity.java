package com.xu.xbasketball.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.di.component.ActivityComponent;
import com.xu.xbasketball.di.component.DaggerActivityComponent;
import com.xu.xbasketball.di.module.ActivityModule;
import com.xu.xbasketball.utils.rxpermissions.RxPermissions;
import com.xu.xbasketball.widget.swipeback.ActivitySwipeBackHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Xu on 2018/3/28.
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivitySwipeBackHelper.SwipeBackCallback {

    protected Activity mContext;
    protected View view;
    private Unbinder unbinder;

    protected RxPermissions rxPermissions;
    protected CompositeDisposable compositeDisposable;

    private boolean useSwipeToExit;
    private ActivitySwipeBackHelper helper;

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

    protected void requestPermission(Consumer<Boolean> consumer, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (rxPermissions == null) {
                rxPermissions = new RxPermissions(this);
            }
            if (compositeDisposable == null) {
                compositeDisposable = new CompositeDisposable();
            }
            compositeDisposable.add(rxPermissions.request(permissions).subscribe(consumer));
        }
    }

    public void viewCreated() {

    }

    public void setUseSwipeToExit(boolean useSwipeToExit) {
        this.useSwipeToExit = useSwipeToExit;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!useSwipeToExit) {
            return super.dispatchTouchEvent(ev);
        }
        if (helper == null) {
            helper = new ActivitySwipeBackHelper(this, this);
        }
        return helper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public void onSwipeBack() {
        Log.i("Activity", "isSliding!");
    }

    public abstract int getLayoutId();

    protected abstract void initInject();

    public abstract void initData();

}
