package com.xu.xbasketball.di;

import android.app.Activity;

import com.xu.xbasketball.di.component.AppComponent;
import com.xu.xbasketball.di.module.ActivityModule;
import com.xu.xbasketball.di.scope.ActivityScope;
import com.xu.xbasketball.ui.court.CourtDetailActivityTest;
import com.xu.xbasketball.ui.court.activity.CourtDetailActivity;
import com.xu.xbasketball.ui.main.activity.MainActivity;
import com.xu.xbasketball.ui.news.activity.NewsDetailActivity;
import com.xu.xbasketball.ui.pic.activity.PicDetailActivity;
import com.xu.xbasketball.ui.video.activity.VideoDetailActivity;

import dagger.Component;

/**
 * Created by Xu on 2019/04/07.
 */

@ActivityScope
@Component(dependencies = TestAppComponent.class, modules = TestActivityModule.class)
public interface TestActivityComponent {
    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(CourtDetailActivityTest courtDetailActivityTest);

    void inject(PicDetailActivity picDetailActivity);

    void inject(VideoDetailActivity videoDetailActivity);

}
