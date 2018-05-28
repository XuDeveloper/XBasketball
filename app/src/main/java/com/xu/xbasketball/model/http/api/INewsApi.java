package com.xu.xbasketball.model.http.api;

import com.xu.xbasketball.model.bean.TencentNewsResultBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhaoxuzhang on 2018/5/28.
 *
 * @author zhaoxuzhang
 */
public interface INewsApi {

    String HOST = "http://tags.open.qq.com/interface/";

    @GET("tag/articles.php?callback=tagListCb&p=1&l=20" +
            "&tag=NBA&oe=gbk&ie=utf-8" +
            "&source=web&site=sports")
    Flowable<TencentNewsResultBean> getNews(@Query("_") String time);

}
