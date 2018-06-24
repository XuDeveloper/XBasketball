package com.xu.xbasketball.di.module;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.http.HttpHelper;
import com.xu.xbasketball.model.http.RetrofitHelper;
import com.xu.xbasketball.model.prefs.PreferencesHelper;
import com.xu.xbasketball.model.prefs.PreferencesHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xu on 2018/3/12.
 */
@Module
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl preferencesHelperImpl) {
        return preferencesHelperImpl;
    }
}
