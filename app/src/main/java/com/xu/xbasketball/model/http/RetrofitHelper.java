package com.xu.xbasketball.model.http;

import com.xu.xbasketball.model.bean.HupuNewsDetailBean;
import com.xu.xbasketball.model.bean.ScoreBoardBean;
import com.xu.xbasketball.model.bean.TencentNewsResultBean;
import com.xu.xbasketball.model.http.api.IBasketballScoreApi;
import com.xu.xbasketball.model.http.api.INewsApi;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by Xu on 2018/3/25.
 */

public class RetrofitHelper implements HttpHelper {

    private IBasketballScoreApi mBasketballScoreService;

    private INewsApi mNewsService;

    @Inject
    public RetrofitHelper(IBasketballScoreApi mBasketballScoreService,
                          INewsApi mNewsService) {
        this.mBasketballScoreService = mBasketballScoreService;
        this.mNewsService = mNewsService;
    }

    @Override
    public Flowable<ScoreBoardBean> getDailyScore(String startTime, String endTime) {
        return mBasketballScoreService.getDailyScore(startTime, endTime);
    }

    @Override
    public Flowable<TencentNewsResultBean> getNews(String time) {
        return mNewsService.getNews(time);
    }

    @Override
    public Flowable<HupuNewsDetailBean> getNewsDetail(String client, String nid) {
        return null;
    }
}
