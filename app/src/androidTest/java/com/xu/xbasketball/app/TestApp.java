package com.xu.xbasketball.app;

import com.xu.xbasketball.di.DaggerTestAppComponent;
import com.xu.xbasketball.di.TestAppComponent;
import com.xu.xbasketball.di.TestAppModule;
import com.xu.xbasketball.di.TestNetModule;
import com.xu.xbasketball.di.component.AppComponent;
import com.xu.xbasketball.di.component.DaggerAppComponent;
import com.xu.xbasketball.di.module.AppModule;
import com.xu.xbasketball.di.module.NetModule;

/**
 * Created by xu on 2019/04/07.
 *
 * @author xu
 */
public class TestApp extends App {

    private TestAppComponent testAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        testAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(getInstance()))
                .testNetModule(new TestNetModule())
                .build();
    }

    public TestAppComponent getTestAppComponent() {
        return testAppComponent;
    }

}
