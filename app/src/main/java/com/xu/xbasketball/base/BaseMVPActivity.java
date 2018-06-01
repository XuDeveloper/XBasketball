package com.xu.xbasketball.base;

import com.xu.xbasketball.utils.SnackBarUtil;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/3/29.
 */

public abstract class BaseMVPActivity<T extends IBasePresenter> extends BaseActivity implements IBaseView {

    @Inject
    protected T mPresenter;

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
