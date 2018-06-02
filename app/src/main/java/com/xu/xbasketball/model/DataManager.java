package com.xu.xbasketball.model;

import com.xu.xbasketball.model.bean.ScoreBoardBean;
import com.xu.xbasketball.model.bean.SinaPicResultBean;
import com.xu.xbasketball.model.bean.TencentNewsResultBean;
import com.xu.xbasketball.model.http.HttpHelper;

import io.reactivex.Flowable;

/**
 * Created by Xu on 2018/3/25.
 */

public class DataManager implements HttpHelper {

    HttpHelper mHttpHelper;

    public DataManager(HttpHelper mHttpHelper) {
        this.mHttpHelper = mHttpHelper;
    }

    @Override
    public Flowable<ScoreBoardBean> getDailyScore(String startTime, String endTime) {
        return mHttpHelper.getDailyScore(startTime, endTime);
    }

    @Override
    public Flowable<TencentNewsResultBean> getNews(String devid) {
        return mHttpHelper.getNews(devid);
    }

    @Override
    public Flowable<SinaPicResultBean> getPics(int page, int num) {
        return mHttpHelper.getPics(page, num);
    }

}
