package com.xu.xbasketball.di.component;

import android.app.Activity;

import com.xu.xbasketball.di.module.ActivityModule;
import com.xu.xbasketball.di.scope.ActivityScope;
import com.xu.xbasketball.ui.court.activity.CourtDetailActivity;
import com.xu.xbasketball.ui.main.activity.MainActivity;
import com.xu.xbasketball.ui.news.activity.NewsDetailActivity;

import dagger.Component;

/**
 * Created by Xu on 2018/3/11.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(CourtDetailActivity courtDetailActivity);

}
