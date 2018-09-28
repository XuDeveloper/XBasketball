package com.xu.xbasketball.model.http;

import com.xu.xbasketball.model.bean.ScoreBoardBean;
import com.xu.xbasketball.model.bean.SinaPicResultBean;
import com.xu.xbasketball.model.bean.TencentNewsResultBean;
import com.xu.xbasketball.model.bean.TencentVideoResultBean;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * Created by Xu on 2018/3/25.
 */

public interface HttpHelper {

    Flowable<ScoreBoardBean> getDailyScore(String startTime, String endTime);

    Flowable<TencentNewsResultBean> getNews(String devid);

    Flowable<SinaPicResultBean> getPics(int page, int num);

    Flowable<ResponseBody> getCourtArticles(int page);

    Flowable<ResponseBody> getCourtArticleDetail(String detail);

    Flowable<TencentVideoResultBean> getVideos(int page);

}
