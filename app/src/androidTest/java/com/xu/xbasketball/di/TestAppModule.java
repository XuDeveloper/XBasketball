package com.xu.xbasketball.di;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.app.TestApp;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.http.HttpHelper;
import com.xu.xbasketball.model.http.RetrofitHelper;
import com.xu.xbasketball.model.prefs.PreferencesHelper;
import com.xu.xbasketball.model.prefs.PreferencesHelperImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xu on 2019/04/07.
 *
 * @author xu
 */
@Module
public class TestAppModule {

    private final TestApp application;

    public TestAppModule(TestApp application) {
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
    PreferencesHelper providePreferencesHelper(PreferencesHelperImpl preferencesHelperImpl) {
        return preferencesHelperImpl;
    }

    @Provides
    @Singleton
    public DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper) {
        return new DataManager(httpHelper, preferencesHelper);
    }

}
