package com.xu.xbasketball.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.xu.xbasketball.utils.SnackBarUtil;

import javax.inject.Inject;

/**
 * Created by zhaoxuzhang on 2018/3/30.
 */

public abstract class BaseMVPFragment<T extends IBasePresenter> extends BaseFragment implements IBaseView {

    @Inject
    T mPresenter;

    View view;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view = ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
        if (mPresenter != null) {
            mPresenter.attach(this);
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        super.onDestroyView();
    }

    @Override
    public void showLoadFailMsg(String msg) {
        SnackBarUtil.show(view, msg);
    }

}
