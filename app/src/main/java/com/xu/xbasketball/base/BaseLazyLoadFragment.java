package com.xu.xbasketball.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.di.component.DaggerFragmentComponent;
import com.xu.xbasketball.di.component.FragmentComponent;
import com.xu.xbasketball.di.module.FragmentModule;
import com.xu.xbasketball.utils.SnackBarUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by Xu on 2017/1/30.
 */

public abstract class BaseLazyLoadFragment<T extends IBasePresenter> extends Fragment implements IBaseView{

    @Inject
    T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .fragmentModule(getFragmentModule())
                .appComponent(App.getAppComponent())
                .build();
    }

    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    /**
     * 视图是否已经初初始化
     */
    protected boolean isInit = false;
    protected boolean isLoad = false;
    /**
     * 标识fragment是否可见
     */
    protected boolean isVisible = false;
    protected final String TAG = "BaseLazyLoadFragment";
    private View view;
    protected LayoutInflater inflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        this.inflater = inflater;
        isInit = true;
        ButterKnife.bind(this, view);
        /**初始化的时候去加载数据**/
        isCanLoadData();
        return view;
    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        isCanLoadData();
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    private void isCanLoadData() {
        if (!isInit) {
            return;
        }

        if (isVisible) {
            lazyLoad();
            isLoad = true;
        } else {
            if (isLoad) {
                stopLoad();
            }
        }
    }

    /**
     * 视图销毁的时候讲Fragment是否初始化的状态变为false
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
    }

    @Override
    public void showLoadFailMsg(String msg) {
        SnackBarUtil.show(view, msg);
    }

    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    protected abstract int getLayoutId();

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以调用此方法
     */
    protected void stopLoad() {
    }

    protected abstract void initInject();

}
