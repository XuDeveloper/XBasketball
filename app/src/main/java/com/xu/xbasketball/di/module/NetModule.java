package com.xu.xbasketball.di.module;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.xu.xbasketball.BuildConfig;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.model.http.api.IBasketballScoreApi;
import com.xu.xbasketball.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Xu on 2018/3/11.
 * @author Xu
 */
@Module
public class NetModule {

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    Retrofit provideBasketballScoreRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, IBasketballScoreApi.HOST);
    }

    @Singleton
    @Provides
    IBasketballScoreApi provideBasketballScoreService(Retrofit retrofit) {
        return retrofit.create(IBasketballScoreApi.class);
    }

    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(Constants.PATH_CACHE);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtil.isNetworkAvailable()) {
                    request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response originalResponse = chain.proceed(request);

                if (NetUtil.isNetworkAvailable()) {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    if ("".equals(cacheControl)) {
                        // 有网络时, 不缓存, 最大保存时长为0
                        return originalResponse.newBuilder()
                                .header("Cache-Control", "public, max-age=0")
                                .removeHeader("Pragma")
                                .build();
                    }
                    return originalResponse.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };
        builder.cache(cache);
        builder.addNetworkInterceptor(cacheInterceptor);
        builder.addInterceptor(cacheInterceptor);

        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        builder.retryOnConnectionFailure(true);

        return builder.build();
    }

}
