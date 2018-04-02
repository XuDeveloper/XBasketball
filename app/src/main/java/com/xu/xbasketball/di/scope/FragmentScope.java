package com.xu.xbasketball.di.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Xu on 2018/4/2.
 */

@Scope
@Retention(RUNTIME)
public @interface FragmentScope {
}
