package com.xu.xbasketball.presenter.court;

import com.xu.xbasketball.base.BaseTest;
import com.xu.xbasketball.base.contract.court.HupuCourtDetailContract;

import org.junit.Test;
import org.mockito.Mock;

import io.reactivex.Flowable;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by xu on 2019/04/05.
 *
 * @author xu
 */
public class HupuCourtDetailPresenterTest extends BaseTest {

    @Mock
    private HupuCourtDetailContract.View view;

    private HupuCourtDetailPresenter hupuCourtDetailPresenter;

    @Override
    protected void init() {
        hupuCourtDetailPresenter = new HupuCourtDetailPresenter(mMockDataManager);
        hupuCourtDetailPresenter.attach(view);
    }

    @Test
    public void getNullCourtArticleDetailAndLoadIntoView() {
        // 只模拟数据为空情况
        when(mMockDataManager.getCourtArticleDetail(anyString())).thenReturn(Flowable.empty());

        hupuCourtDetailPresenter.getCourtArticleDetail("http://dev-xu.cn");

        // 测试model是否有获取数据
        verify(mMockDataManager).getCourtArticleDetail("http://dev-xu.cn");

        // 测试view是否有调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
    }

    @Override
    protected void clear() {
        hupuCourtDetailPresenter.detach();
    }
}