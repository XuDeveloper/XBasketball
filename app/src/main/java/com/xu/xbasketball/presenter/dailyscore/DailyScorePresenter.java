package com.xu.xbasketball.presenter.dailyscore;

import com.xu.xbasketball.base.BaseSubscriber;
import com.xu.xbasketball.base.IBaseView;
import com.xu.xbasketball.base.RxPresenter;
import com.xu.xbasketball.base.contract.dailyscore.DailyScoreContract;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.bean.GameBean;
import com.xu.xbasketball.model.bean.ScoreBoardBean;
import com.xu.xbasketball.utils.DateUtil;
import com.xu.xbasketball.utils.RxUtil;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Xu on 2018/3/11.
 *
 * @author Xu
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
                        if (scoreBoardBean != null && scoreBoardBean.getData() != null) {
                            Map<String, List<GameBean>> data = scoreBoardBean.getData();
                            List<GameBean> gameBeans = data.get(DateUtil.getTodayDate());
                            mView.showDailyScore(gameBeans);
                        } else {
                            mView.showLoadFailMsg("暂无数据，请重试！");
                        }
                    }

                    @Override
                    public IBaseView getBaseView() {
                        return mView;
                    }
                }));
    }
}
