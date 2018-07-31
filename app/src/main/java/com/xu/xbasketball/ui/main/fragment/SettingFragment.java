package com.xu.xbasketball.ui.main.fragment;

import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseMVPFragment;
import com.xu.xbasketball.base.contract.main.SettingContract;
import com.xu.xbasketball.presenter.main.SettingPresenter;

import butterknife.BindView;

/**
 * Created by xu on 2018/7/22.
 */
public class SettingFragment extends BaseMVPFragment<SettingPresenter> implements SettingContract.View, SwitchCompat.OnCheckedChangeListener {

    @BindView(R.id.switch_auto_cache)
    SwitchCompat switchAutoCache;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {
        switchAutoCache.setChecked(mPresenter.getNoImageState());
        switchAutoCache.setOnCheckedChangeListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.switch_auto_cache:
                mPresenter.setNoImageState(b);
                break;
        }
    }
}
