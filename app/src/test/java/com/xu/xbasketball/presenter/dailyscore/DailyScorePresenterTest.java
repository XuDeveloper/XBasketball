package com.xu.xbasketball.presenter.dailyscore;

import com.xu.xbasketball.base.BaseTest;
import com.xu.xbasketball.base.contract.dailyscore.DailyScoreContract;
import com.xu.xbasketball.model.bean.ScoreBoardBean;

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
public class DailyScorePresenterTest extends BaseTest {

    @Mock
    private DailyScoreContract.View view;

    private DailyScorePresenter dailyScorePresenter;

    @Override
    protected void init() {
        dailyScorePresenter = new DailyScorePresenter(mMockDataManager);
        dailyScorePresenter.attach(view);
    }

    @Test
    public void getDailyScoreAndLoadIntoView() {
        ScoreBoardBean scoreBoardBean = new ScoreBoardBean();
        when(mMockDataManager.getDailyScore(anyString(), anyString())).thenReturn(Flowable.just(scoreBoardBean));

        dailyScorePresenter.getDailyScore("2018-04-05", "2018-04-05");

        // 测试model是否有获取数据
        verify(mMockDataManager).getDailyScore(anyString(), anyString());

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
    }

    @Test
    public void getNullDailyScoreAndLoadIntoView() {
        ScoreBoardBean scoreBoardBean = new ScoreBoardBean();
        scoreBoardBean.setData(null);
        when(mMockDataManager.getDailyScore(anyString(), anyString())).thenReturn(Flowable.just(scoreBoardBean));

        dailyScorePresenter.getDailyScore("2019-04-05", "2019-04-05");

        // 测试model是否有获取数据
        verify(mMockDataManager).getDailyScore(anyString(), anyString());

        // 测试view是否调用相应接口
        verify(view).showProgress();
        verify(view).hideProgress();
        verify(view).showLoadFailMsg(anyString());
    }

    @Override
    protected void clear() {
        dailyScorePresenter.detach();
    }
}