package com.xu.xbasketball.ui.court.fragment;

import com.xu.xbasketball.base.BaseMVPFragment;
import com.xu.xbasketball.base.contract.court.HupuCourtContract;
import com.xu.xbasketball.model.bean.HupuCourtBean;
import com.xu.xbasketball.presenter.court.HupuCourtPresenter;

import java.util.List;

/**
 * Created by xu on 2018/8/24.
 */
public class CourtFragment extends BaseMVPFragment<HupuCourtPresenter> implements HupuCourtContract.View {

    @Override
    protected void initInject() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void showCourtArticles(List<HupuCourtBean> data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
