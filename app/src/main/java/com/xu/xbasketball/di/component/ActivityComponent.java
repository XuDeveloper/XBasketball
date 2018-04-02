package com.xu.xbasketball.di.component;

import com.xu.xbasketball.di.module.ActivityModule;
import com.xu.xbasketball.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by Xu on 2018/3/11.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
//    void inject();
}
