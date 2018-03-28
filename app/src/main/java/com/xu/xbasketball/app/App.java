package com.xu.xbasketball.app;

import android.app.Activity;
import android.app.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Xu on 2018/3/12.
 */

public class App extends Application {

    private static App instance;
    private Set<Activity> allActivities;

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

    public void addActivity(Activity activity) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(Activity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }
}
