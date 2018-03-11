package com.xu.xbasketball.di.module;

import com.xu.xbasketball.base.contract.dailyscore.DailyScoreContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xu on 2018/3/11.
 */
@Module
public class DailyScoreModule {

    private DailyScoreContract.View mView;

    public DailyScoreModule(DailyScoreContract.View view) {
        mView = view;
    }

    @Provides
    public DailyScoreContract.View provideView() {
        return mView;
    }

}
