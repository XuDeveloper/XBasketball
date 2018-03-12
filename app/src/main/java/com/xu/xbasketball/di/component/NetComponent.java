package com.xu.xbasketball.di.component;

import com.xu.xbasketball.di.module.NetModule;
import com.xu.xbasketball.model.http.api.IBasketballScoreApi;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Xu on 2018/3/12.
 * @author Xu
 */

@Component(modules = NetModule.class)
public interface NetComponent {
    IBasketballScoreApi getBasketballScoreApiService();
    Retrofit getRetrofit();
}
