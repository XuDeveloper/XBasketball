package com.xu.xbasketball.model.http;

import com.xu.xbasketball.model.bean.ScoreBoardBean;
import com.xu.xbasketball.model.bean.SinaPicResultBean;
import com.xu.xbasketball.model.bean.TencentNewsResultBean;
import com.xu.xbasketball.model.http.api.IBasketballScoreApi;
import com.xu.xbasketball.model.http.api.ICourtApi;
import com.xu.xbasketball.model.http.api.INewsApi;
import com.xu.xbasketball.model.http.api.IPicApi;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * Created by Xu on 2018/3/25.
 */

public class RetrofitHelper implements HttpHelper {

    private IBasketballScoreApi mBasketballScoreService;

    private INewsApi mNewsService;

    private IPicApi mPicService;

    private ICourtApi mCourtArticlesService;

    @Inject
    public RetrofitHelper(IBasketballScoreApi mBasketballScoreService,
                          INewsApi mNewsService, IPicApi mPicService, ICourtApi mCourtArticlesService) {
        this.mBasketballScoreService = mBasketballScoreService;
        this.mNewsService = mNewsService;
        this.mPicService = mPicService;
        this.mCourtArticlesService = mCourtArticlesService;
    }

    @Override
    public Flowable<ScoreBoardBean> getDailyScore(String startTime, String endTime) {
        return mBasketballScoreService.getDailyScore(startTime, endTime);
    }

    @Override
    public Flowable<TencentNewsResultBean> getNews(String devid) {
        return mNewsService.getNews(devid);
    }

    @Override
    public Flowable<SinaPicResultBean> getPics(int page, int num) {
        return mPicService.getPics(page, num);
    }

    @Override
    public Flowable<ResponseBody> getCourtArticles(int page) {
        return mCourtArticlesService.getCourtArticles(page);
    }

    @Override
    public Flowable<ResponseBody> getCourtArticleDetail(String detail) {
        return mCourtArticlesService.getCourtArticleDetail(detail);
    }
}
