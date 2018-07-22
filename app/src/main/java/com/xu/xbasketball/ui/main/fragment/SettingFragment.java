package com.xu.xbasketball.ui.main.fragment;

import com.xu.xbasketball.base.BaseFragment;
import com.xu.xbasketball.base.BaseMVPFragment;
import com.xu.xbasketball.base.contract.main.SettingContract;
import com.xu.xbasketball.presenter.main.SettingPresenter;

/**
 * Created by xu on 2018/7/22.
 */
public class SettingFragment extends BaseMVPFragment<SettingPresenter> implements SettingContract.View {



    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showLoadFailMsg(String msg) {

    }
}
