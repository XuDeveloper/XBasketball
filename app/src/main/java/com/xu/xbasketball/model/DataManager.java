package com.xu.xbasketball.model;

import android.util.Log;

import com.xu.xbasketball.model.bean.ScoreBoardBean;
import com.xu.xbasketball.model.bean.SinaPicResultBean;
import com.xu.xbasketball.model.bean.TencentNewsResultBean;
import com.xu.xbasketball.model.bean.TencentVideoResultBean;
import com.xu.xbasketball.model.http.HttpHelper;
import com.xu.xbasketball.model.prefs.PreferencesHelper;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * Created by Xu on 2018/3/25.
 */

public class DataManager implements HttpHelper, PreferencesHelper {

    HttpHelper mHttpHelper;
    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper mHttpHelper, PreferencesHelper mPreferencesHelper) {
        this.mHttpHelper = mHttpHelper;
        this.mPreferencesHelper = mPreferencesHelper;
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

    @Override
    public Flowable<ResponseBody> getCourtArticles(int page) {
        return mHttpHelper.getCourtArticles(page);
    }

    @Override
    public Flowable<ResponseBody> getCourtArticleDetail(String detail) {
        return mHttpHelper.getCourtArticleDetail(detail);
    }

    @Override
    public Flowable<TencentVideoResultBean> getVideos(int page) {
        return mHttpHelper.getVideos(page);
    }

    @Override
    public boolean getNoImageState() {
        return mPreferencesHelper.getNoImageState();
    }

    @Override
    public void setNoImageState(boolean state) {
        mPreferencesHelper.setNoImageState(state);
    }
}
