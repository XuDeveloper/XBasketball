package com.xu.xbasketball.model.http.api;

import com.xu.xbasketball.model.bean.ScoreBoardBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Xu on 2018/3/11.
 * 获取每日战报API
 */

public interface IBasketballScoreApi {

    String HOST = "http://matchweb.sports.qq.com/kbs/";

    /**
     * 获取每日所有比赛的比分数据
     * e.g. "http://matchweb.sports.qq.com/kbs/list?columnId=100000&startTime=2018-03-10&endTime=2018-03-10"
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    @GET("list?columnId=100000")
    Flowable<ScoreBoardBean> getDailyScore(@Query("startTime") String startTime, @Query("endTime") String endTime);

}
