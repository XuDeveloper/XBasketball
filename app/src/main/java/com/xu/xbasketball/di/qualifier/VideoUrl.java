package com.xu.xbasketball.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Xu on 2018/9/22.
 *
 * @author Xu
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface VideoUrl {

}
