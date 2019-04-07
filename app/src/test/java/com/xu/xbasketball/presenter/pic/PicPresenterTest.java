package com.xu.xbasketball.presenter.pic;

import com.xu.xbasketball.base.BaseTest;
import com.xu.xbasketball.base.contract.pic.PicContract;
import com.xu.xbasketball.model.bean.SinaPicResultBean;

import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Flowable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xu on 2019/04/05.
 *
 * @author xu
 */
public class PicPresenterTest extends BaseTest  {

    @Mock
    private PicContract.View view;

    private PicPresenter picPresenter;

    @Override
    protected void init() {
        picPresenter = new PicPresenter(mMockDataManager);
        picPresenter.attach(view);
    }

    @Test
    public void getPicsAndLoadIntoView() {
        SinaPicResultBean sinaPicResultBean = new SinaPicResultBean();
        when(mMockDataManager.getPics(anyInt(), anyInt())).thenReturn(Flowable.just(sinaPicResultBean));

        picPresenter.getPics(1, 10);

        // 测试model是否有获取数据
        verify(mMockDataManager).getPics(1, 10);

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
    }

    @Test
    public void getNullPicsAndLoadIntoView() {
        SinaPicResultBean sinaPicResultBean = new SinaPicResultBean();
        sinaPicResultBean.setData(null);
        when(mMockDataManager.getPics(anyInt(), anyInt())).thenReturn(Flowable.just(sinaPicResultBean));

        picPresenter.getPics(1, 10);

        // 测试model是否有获取数据
        verify(mMockDataManager).getPics(1, 10);

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).showLoadFailMsg(anyString());
    }

    @Override
    protected void clear() {
        picPresenter.detach();
    }
}