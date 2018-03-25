package com.xu.xbasketball.app;

import android.app.Application;

/**
 * Created by Xu on 2018/3/12.
 */

public class App extends Application {

    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initNet();
    }

    private void initNet() {

    }
}
