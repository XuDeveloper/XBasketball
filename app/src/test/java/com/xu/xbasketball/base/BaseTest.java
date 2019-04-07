package com.xu.xbasketball.base;

import com.xu.xbasketball.model.DataManager;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Created by xu on 2019/04/05.
 *
 * @author xu
 */
public abstract class BaseTest {

    @ClassRule
    public static final RxImmediateSchedulerRule schedulers = new RxImmediateSchedulerRule();

    @Mock
    protected DataManager mMockDataManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        init();
    }

    @After
    public void tearDown() {
        clear();
    }

    protected abstract void init();

    protected abstract void clear();

}
