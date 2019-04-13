package com.xu.xbasketball.presenter.court;

import com.xu.xbasketball.base.BaseTest;
import com.xu.xbasketball.base.contract.court.HupuCourtContract;

import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Flowable;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xu on 2019/04/01.
 * <p>
 *
 * @author xu
 */
public class HupuCourtPresenterTest extends BaseTest {

    @Mock
    private HupuCourtContract.View view;

    private HupuCourtPresenter hupuCourtPresenter;

    @Override
    protected void init() {
        hupuCourtPresenter = new HupuCourtPresenter(mMockDataManager);
        hupuCourtPresenter.attach(view);
    }

    @Test
    public void getNullCourtArticlesAndLoadIntoView() {
        // 只模拟数据为空情况
        when(mMockDataManager.getCourtArticles(anyInt())).thenReturn(Flowable.empty());

        hupuCourtPresenter.getCourtArticles(1);

        // 测试model是否有获取数据
        verify(mMockDataManager).getCourtArticles(1);

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
    }

    @Override
    protected void clear() {
        hupuCourtPresenter.detach();
    }

}