package com.xu.xbasketball.model.http.api;

import com.xu.xbasketball.model.bean.TencentNewsResultBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Xu on 2018/5/28.
 *
 * @author Xu
 */
public interface INewsApi {

    String HOST = "https://r.inews.qq.com/";

    @GET("getQQNewsUnreadList?last_id=&chlid=news_news_nba")
    Flowable<TencentNewsResultBean> getNews(@Query("devid") String devid);

}
