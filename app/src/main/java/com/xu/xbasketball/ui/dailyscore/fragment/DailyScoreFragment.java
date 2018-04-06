package com.xu.xbasketball.ui.dailyscore.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseLazyLoadFragment;
import com.xu.xbasketball.base.contract.dailyscore.DailyScoreContract;
import com.xu.xbasketball.model.bean.GameBean;
import com.xu.xbasketball.presenter.dailyscore.DailyScorePresenter;
import com.xu.xbasketball.ui.dailyscore.adapter.DailyscoreAdapter;
import com.xu.xbasketball.utils.DateUtil;

import java.util.List;

import butterknife.BindView;

/**
 * @author Xu
 */
public class DailyScoreFragment extends BaseLazyLoadFragment<DailyScorePresenter> implements DailyScoreContract.View {

    @BindView(R.id.rv_dailyscore)
    RecyclerView rvDailyscore;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private DailyscoreAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dailyscore;
    }

    @Override
    protected void initDatas() {
        adapter = new DailyscoreAdapter(mContext);
        // todo recycleview初始化
        rvDailyscore.setAdapter(adapter);
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getDailyScore(DateUtil.getTodayDate(), DateUtil.getTodayDate());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDailyScore(DateUtil.getTodayDate(), DateUtil.getTodayDate());
            }
        });
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showDailyScore(List<GameBean> data) {
        adapter.updateData(data);
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

}
