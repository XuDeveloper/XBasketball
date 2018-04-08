package com.xu.xbasketball.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.di.component.DaggerFragmentComponent;
import com.xu.xbasketball.di.component.FragmentComponent;
import com.xu.xbasketball.di.module.FragmentModule;
import com.xu.xbasketball.utils.SnackBarUtil;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/3/30.
 *
 * @author Xu
 */

public abstract class BaseMVPFragment<T extends IBasePresenter> extends BaseFragment implements IBaseView {

    @Inject
    T mPresenter;

    View view;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.view = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
        initInject();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroyView();
    }

    protected abstract void initInject();

    @Override
    public void showLoadFailMsg(String msg) {
        SnackBarUtil.show(view, msg);
    }

}
