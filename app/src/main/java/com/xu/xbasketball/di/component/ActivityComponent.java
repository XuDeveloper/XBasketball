package com.xu.xbasketball.di.component;

import com.xu.xbasketball.di.module.DailyScoreModule;
import com.xu.xbasketball.di.scope.UserScope;

import dagger.Component;

/**
 * Created by Xu on 2018/3/11.
 */

@UserScope
@Component(modules = DailyScoreModule.class)
public interface ActivityComponent {
//    void inject();
}
