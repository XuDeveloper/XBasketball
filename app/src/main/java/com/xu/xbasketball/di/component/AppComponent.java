package com.xu.xbasketball.di.component;

import com.xu.xbasketball.app.App;
import com.xu.xbasketball.di.module.AppModule;
import com.xu.xbasketball.di.module.NetModule;
import com.xu.xbasketball.model.DataManager;
import com.xu.xbasketball.model.http.RetrofitHelper;
import com.xu.xbasketball.model.prefs.PreferencesHelperImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xu on 2018/3/12.
 *
 * @author Xu
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {

    App getApplicationContext();

    DataManager getDataManager();

    RetrofitHelper retrofitHelper();

    PreferencesHelperImpl preferencesHelper();

}
