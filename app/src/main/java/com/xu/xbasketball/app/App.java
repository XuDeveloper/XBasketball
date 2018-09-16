package com.xu.xbasketball.app;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

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
    public static int SCREEN_HEIGHT = -1;
    public static int SCREEN_WIDTH = -1;
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

        //初始化屏幕宽高
        getScreenSize();

        init();
    }

    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
//        DIMEN_RATE = dm.density / 1.0F;
//        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
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

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (BaseActivity baseActivity: allActivities) {
                    baseActivity.finish();
                }
            }
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
