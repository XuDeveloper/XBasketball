package com.xu.xbasketball.model.http;

import com.xu.xbasketball.model.bean.HupuNewsBean;
import com.xu.xbasketball.model.bean.ScoreBoardBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by Xu on 2018/3/25.
 */

public interface HttpHelper {

    Flowable<ScoreBoardBean> getDailyScore(String startTime, String endTime);

    Flowable<List<HupuNewsBean>> getNews(String client);

}
