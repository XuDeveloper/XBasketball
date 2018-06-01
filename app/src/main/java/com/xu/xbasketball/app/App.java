package com.xu.xbasketball.app;

import android.app.Application;

import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.di.component.AppComponent;
import com.xu.xbasketball.di.component.DaggerAppComponent;
import com.xu.xbasketball.di.module.AppModule;
import com.xu.xbasketball.di.module.NetModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Xu on 2018/3/12.
 */

public class App extends Application {

    public static AppComponent appComponent;
    private static App instance;
    private Set<BaseActivity> allActivities;

    public static synchronized App getInstance() {
        return instance;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .netModule(new NetModule())
                    .build();
        }
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }

    private void init() {
    }

    public void addActivity(BaseActivity activity) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }
}
