package com.xu.xbasketball.base;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 * Created by xu on 2019/04/06.
 *
 * @author xu
 */
@RunWith(AndroidJUnit4.class)
public abstract class BaseUITest {

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
        init();
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
        clear();
    }

    public abstract void init();

    public abstract void clear();

}
