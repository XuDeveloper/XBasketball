package com.xu.xbasketball.model.http;

import com.xu.xbasketball.model.bean.HupuResultBean;
import com.xu.xbasketball.model.bean.ScoreBoardBean;

import io.reactivex.Flowable;

/**
 * Created by Xu on 2018/3/25.
 */

public interface HttpHelper {

    Flowable<ScoreBoardBean> getDailyScore(String startTime, String endTime);

    Flowable<HupuResultBean> getNews(String client);

}
