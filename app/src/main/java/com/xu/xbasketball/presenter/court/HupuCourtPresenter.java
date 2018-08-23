package com.xu.xbasketball.presenter.court;

import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.court.HupuCourtContract;
import com.xu.xbasketball.model.DataManager;

import javax.inject.Inject;

/**
 * Created by xu on 2018/8/23.
 */
public class HupuCourtPresenter extends RxPresenter<HupuCourtContract.View> implements HupuCourtContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public HupuCourtPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getContent(int page) {

    }
}
