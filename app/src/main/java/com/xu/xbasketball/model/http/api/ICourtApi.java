package com.xu.xbasketball.model.http.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xu on 2018/8/23.
 */
public interface ICourtApi {

    String HOST = "https://m.hupu.com/bbs/";

    @GET("130-{page}")
    Flowable<ResponseBody> getCourtArticles(@Path("page") int page);

    @GET("{detail}")
    Flowable<ResponseBody> getCourtArticleDetail(@Path("detail") String detail);

}
