package com.xu.xbasketball.presenter.main;

import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.main.SettingContract;
import com.xu.xbasketball.model.DataManager;

import javax.inject.Inject;

/**
 * Created by xu on 2018/7/22.
 */
public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public SettingPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void setNoImageState(boolean state) {
        mDataManager.setNoImageState(state);
    }

    @Override
    public boolean getNoImageState() {
        return mDataManager.getNoImageState();
    }
}

