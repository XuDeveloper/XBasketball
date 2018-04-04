package com.xu.xbasketball.di.component;

import android.app.Activity;

import com.xu.xbasketball.di.module.FragmentModule;
import com.xu.xbasketball.di.scope.FragmentScope;
import com.xu.xbasketball.ui.dailyscore.DailyScoreFragment;

import dagger.Component;

/**
 * @author Xu
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(DailyScoreFragment dailyScoreFragment);

}
