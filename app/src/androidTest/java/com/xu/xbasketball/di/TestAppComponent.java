package com.xu.xbasketball.di;

import com.xu.xbasketball.di.component.AppComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xu on 2018/3/12.
 *
 * @author Xu
 */
@Singleton
@Component(modules = {TestAppModule.class, TestNetModule.class})
public interface TestAppComponent extends AppComponent {
}
