package com.xu.xbasketball.presenter.video;

import com.xu.xbasketball.base.BaseTest;
import com.xu.xbasketball.base.contract.video.VideoContract;
import com.xu.xbasketball.model.bean.TencentVideoResultBean;

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
public class VideoPresenterTest extends BaseTest {

    @Mock
    private VideoContract.View view;

    private VideoPresenter videoPresenter;

    @Override
    protected void init() {
        videoPresenter = new VideoPresenter(mMockDataManager);
        videoPresenter.attach(view);
    }

    @Test
    public void getVideosAndLoadIntoView() {
        TencentVideoResultBean resultBean = new TencentVideoResultBean();
        when(mMockDataManager.getVideos(anyInt())).thenReturn(Flowable.just(resultBean));

        videoPresenter.getVideos(1);

        // 测试model是否有获取数据
        verify(mMockDataManager).getVideos(1);

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
    }

    @Test
    public void getNullVideosAndLoadIntoView() {
        TencentVideoResultBean resultBean = new TencentVideoResultBean();
        resultBean.setData(null);
        when(mMockDataManager.getVideos(anyInt())).thenReturn(Flowable.just(resultBean));

        videoPresenter.getVideos(1);

        // 测试model是否有获取数据
        verify(mMockDataManager).getVideos(1);

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).showLoadFailMsg(anyString());
    }

    @Override
    protected void clear() {
        videoPresenter.detach();
    }
}