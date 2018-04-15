package com.xu.xbasketball.model.http.api;

import com.xu.xbasketball.model.bean.HupuResultBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Xu on 2018/4/8.
 *
 * @author Xu
 */
public interface INewsApi {

    String HOST = "http://games.mobileapi.hupu.com/1/7.1.18/nba/";

    /**
     * 获取每日新闻
     * @param client
     * @return
     */
    @GET("getNews?crt=1523456346025&night=0&channel=wandoujia" +
            "&sign=c7b787408a9c10a50713996c8566ac1b" +
            "&time_zone=Asia%2FShanghai" +
            "&android_id=3a6d5d1eb7c4f376&entrance=-1")
    Flowable<HupuResultBean> getNews(@Query("client") String client);

}
