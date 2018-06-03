package com.xu.xbasketball.model.http.api;

import com.xu.xbasketball.model.bean.SinaPicResultBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhaoxuzhang on 2018/6/2.
 *
 * @author zhaoxuzhang
 */
public interface IPicApi {

    // http://api.slide.news.sina.com.cn/interface/api_album.php?size=img&ch_id=2&sub_ch=k&page=1&num=50

    String HOST = "http://api.slide.news.sina.com.cn/";

    @GET("interface/api_album.php?size=img&ch_id=2&sub_ch=k")
    Flowable<SinaPicResultBean> getPics(@Query("page") int page, @Query("num") int num);

}
