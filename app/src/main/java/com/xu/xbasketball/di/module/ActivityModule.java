package com.xu.xbasketball.di.module;

import android.app.Activity;

import com.xu.xbasketball.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xu on 2018/3/13.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}
