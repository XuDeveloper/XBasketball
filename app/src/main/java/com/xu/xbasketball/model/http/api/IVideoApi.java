package com.xu.xbasketball.model.http.api;

import com.xu.xbasketball.model.bean.TencentVideoResultBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Xu on 2018/9/24.
 *
 * @author Xu
 */
public interface IVideoApi {

    // https://pacaio.match.qq.com/vlike/cluster?cid=8&page=1
    String HOST = "https://pacaio.match.qq.com/vlike/";

    @GET("cluster?cid=8")
    Flowable<TencentVideoResultBean> getVideos(@Query("page") int page);

}
