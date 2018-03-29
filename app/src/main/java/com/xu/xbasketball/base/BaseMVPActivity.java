package com.xu.xbasketball.base;

import android.view.View;
import android.view.ViewGroup;

import com.xu.xbasketball.utils.SnackBarUtil;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/3/29.
 */

public abstract class BaseMVPActivity<T extends IBasePresenter> extends BaseActivity implements IBaseView {

    @Inject
    T mPresenter;

    View view;

//    protected ActivityComponent getActivityComponent(){
//        return  DaggerActivityComponent.builder()
//                .appComponent(App.getAppComponent())
//                .activityModule(getActivityModule())
//                .build();
//    }
//
//    protected ActivityModule getActivityModule(){
//        return new ActivityModule(this);
//    }

    @Override
    public void viewCreated() {
        super.viewCreated();
        view = ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroy();
    }

    @Override
    public void showLoadFailMsg(String msg) {
        SnackBarUtil.show(view, msg);
    }

}
