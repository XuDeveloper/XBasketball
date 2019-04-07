package com.xu.xbasketball.presenter.main;

import com.xu.xbasketball.base.BaseTest;

import org.junit.Test;

import static org.mockito.Mockito.verify;

/**
 * Created by xu on 2019/04/05.
 *
 * @author xu
 */
public class SettingPresenterTest extends BaseTest {

    private SettingPresenter settingPresenter;

    @Override
    protected void init() {
        settingPresenter = new SettingPresenter(mMockDataManager);
    }

    @Test
    public void setNoImageStateOn() {
        settingPresenter.setNoImageState(true);
        verify(mMockDataManager).setNoImageState(true);
    }

    @Test
    public void setNoImageStateOff() {
        settingPresenter.setNoImageState(false);
        verify(mMockDataManager).setNoImageState(false);
    }

    @Test
    public void getNoImageState() {
        settingPresenter.getNoImageState();
        verify(mMockDataManager).getNoImageState();
    }

    @Override
    protected void clear() {
        settingPresenter.detach();
    }
}