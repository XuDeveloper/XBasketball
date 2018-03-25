package com.xu.xbasketball.presenter.dailyscore;

import com.xu.xbasketball.base.BaseSubscriber;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.dailyscore.DailyScoreContract;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.bean.ScoreBoardBean;
import com.xu.xbasketball.utils.RxUtil;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/3/11.
 */

public class DailyScorePresenter extends RxPresenter<DailyScoreContract.View> implements DailyScoreContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public DailyScorePresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void getDailyScore(String startTime, String endTime) {
        addSubscribe(mDataManager.getDailyScore(startTime, endTime)
                .compose(RxUtil.<ScoreBoardBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<ScoreBoardBean>() {
                    @Override
                    public void onNext(ScoreBoardBean scoreBoardBean) {
                        mView.showDailyScore(scoreBoardBean);
                    }

                    @Override
                    public IBaseView getBaseView() {
                        return mView;
                    }
                }));
    }
}
