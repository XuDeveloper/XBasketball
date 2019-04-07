package com.xu.xbasketball.di;

import android.app.Activity;

import com.xu.xbasketball.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xu on 2019/04/07.
 */

@Module
public class TestActivityModule {

    private Activity mActivity;

    public TestActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }

}
