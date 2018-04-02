package com.xu.xbasketball.di.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.xu.xbasketball.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xu on 2018/4/2.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }

}
