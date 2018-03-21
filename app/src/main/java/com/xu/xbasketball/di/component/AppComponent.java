package com.xu.xbasketball.di.component;

import com.xu.xbasketball.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Xu on 2018/3/12.
 *
 * @author Xu
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

}
