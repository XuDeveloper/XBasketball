package com.xu.xbasketball.presenter.news;

import com.xu.xbasketball.base.BaseTest;
import com.xu.xbasketball.base.contract.news.NewsContract;
import com.xu.xbasketball.model.bean.TencentNewsResultBean;

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
public class NewsPresenterTest extends BaseTest {

    @Mock
    private NewsContract.View view;

    private NewsPresenter newsPresenter;

    @Override
    protected void init() {
        newsPresenter = new NewsPresenter(mMockDataManager);
        newsPresenter.attach(view);
    }

    @Test
    public void getNewsAndLoadIntoView() {
        TencentNewsResultBean resultBean = new TencentNewsResultBean();
        when(mMockDataManager.getNews()).thenReturn(Flowable.just(resultBean));

        newsPresenter.getNews();

        // 测试model是否有获取数据
        verify(mMockDataManager).getNews();

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
    }

    @Test
    public void getNullNewsAndLoadIntoView() {
        TencentNewsResultBean resultBean = new TencentNewsResultBean();
        resultBean.setData(null);
        when(mMockDataManager.getNews()).thenReturn(Flowable.just(resultBean));

        newsPresenter.getNews();

        // 测试model是否有获取数据
        verify(mMockDataManager).getNews();

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).showLoadFailMsg(anyString());
    }

    @Override
    protected void clear() {
        newsPresenter.detach();
    }
}