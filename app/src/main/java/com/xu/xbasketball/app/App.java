package com.xu.xbasketball.app;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.di.component.AppComponent;
import com.xu.xbasketball.di.component.DaggerAppComponent;
import com.xu.xbasketball.di.module.AppModule;
import com.xu.xbasketball.di.module.NetModule;
import com.xu.xbasketball.model.http.webview.WebViewWrapper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by Xu on 2018/3/12.
 */

public class App extends Application {

    public static AppComponent appComponent;
    public static int SCREEN_HEIGHT = -1;
    public static int SCREEN_WIDTH = -1;
    private static App instance;
    private List<BaseActivity> allActivities;
    private WebViewWrapper mGlobalWebview;

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
        mGlobalWebview = new WebViewWrapper(getApplicationContext());
        mGlobalWebview.setScrollContainer(false);
        mGlobalWebview.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mGlobalWebview.setVerticalScrollBarEnabled(true);
    }

    public WebViewWrapper getGlobalWebview() {
        return mGlobalWebview;
    }

    public void addActivity(BaseActivity activity) {
        if (allActivities == null) {
            allActivities = new LinkedList<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(BaseActivity activity) {
        if (allActivities != null) {
            allActivities.remove(activity);
        }
    }

    public BaseActivity getPreviousActivity() {
        int count = allActivities.size();
        if (count < 2) {
            return null;
        }
        return allActivities.get(count - 2);
    }

    public void exitApp() {
        if (allActivities != null) {
            synchronized (allActivities) {
                for (BaseActivity baseActivity: allActivities) {
                    baseActivity.finish();
                }
            }
        }
        mGlobalWebview.destroyWebView();
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
    
}
