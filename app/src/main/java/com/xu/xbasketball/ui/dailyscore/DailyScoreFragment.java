package com.xu.xbasketball.ui.dailyscore;

import com.xu.xbasketball.base.BaseLazyLoadFragment;
import com.xu.xbasketball.base.contract.dailyscore.DailyScoreContract;
import com.xu.xbasketball.model.bean.GameBean;
import com.xu.xbasketball.presenter.dailyscore.DailyScorePresenter;

import java.util.List;

/**
 * @author Xu
 */
public class DailyScoreFragment extends BaseLazyLoadFragment<DailyScorePresenter> implements DailyScoreContract.View{


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showDailyScore(List<GameBean> data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

}
