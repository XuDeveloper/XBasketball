package com.xu.xbasketball.di;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.xu.xbasketball.di.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Xu on 2019/04/07.
 */

@Module
public class TestFragmentModule {

    private Fragment fragment;

    public TestFragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity() {
        return fragment.getActivity();
    }

}
