package com.xu.xbasketball.model.http;

import com.xu.xbasketball.model.bean.ScoreBoardBean;
import com.xu.xbasketball.model.http.api.IBasketballScoreApi;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Xu on 2018/3/25.
 */

public class RetrofitHelper implements HttpHelper {

    private IBasketballScoreApi mBasketballScoreService;

    @Inject
    public RetrofitHelper(IBasketballScoreApi mBasketballScoreService) {
        this.mBasketballScoreService = mBasketballScoreService;
    }

    @Override
    public Flowable<ScoreBoardBean> getDailyScore(String startTime, String endTime) {
        return mBasketballScoreService.getDailyScore(startTime, endTime);
    }
}
