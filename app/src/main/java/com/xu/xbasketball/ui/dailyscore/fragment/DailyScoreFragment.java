package com.xu.xbasketball.ui.dailyscore.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseLazyLoadFragment;
import com.xu.xbasketball.base.contract.dailyscore.DailyScoreContract;
import com.xu.xbasketball.model.bean.GameBean;
import com.xu.xbasketball.presenter.dailyscore.DailyScorePresenter;
import com.xu.xbasketball.ui.dailyscore.adapter.DailyscoreAdapter;
import com.xu.xbasketball.utils.DateUtil;
import com.xu.xbasketball.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Xu on 2018/4/7.
 *
 * @author Xu
 */
public class DailyScoreFragment extends BaseLazyLoadFragment<DailyScorePresenter> implements DailyScoreContract.View {

    @BindView(R.id.rv_daily_score)
    RecyclerView rvDailyScore;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fab_daily_score_back_to_top)
    FloatingActionButton fabDailyScoreBackToTop;

    private DailyscoreAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    private boolean isLoadingMore = false;

    @Override
    protected void initDatas() {
        adapter = new DailyscoreAdapter(mContext);
        mLayoutManager = new LinearLayoutManager(mContext);
        rvDailyScore.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        rvDailyScore.setHasFixedSize(true);
        rvDailyScore.setLayoutManager(mLayoutManager);
        rvDailyScore.setItemAnimator(new DefaultItemAnimator());
        rvDailyScore.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dailyscore;
    }

    @Override
    protected void lazyLoad() {
        fabDailyScoreBackToTop.setVisibility(View.GONE);
        mPresenter.getDailyScore(DateUtil.getTodayDate(), DateUtil.getTodayDate());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDailyScore(DateUtil.getTodayDate(), DateUtil.getTodayDate());
            }
        });

        rvDailyScore.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = mLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem >= adapter.getItemCount() - 1 && !isLoadingMore) {
                    isLoadingMore = true;
                    mPresenter.getDailyScore(DateUtil.getTodayDate(), DateUtil.getTodayDate());
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 判断是否超过一屏
                    if (firstVisibleItemPosition == 0) {
                        fabDailyScoreBackToTop.setVisibility(View.GONE);
                    } else {
                        fabDailyScoreBackToTop.setVisibility(View.VISIBLE);
                    }
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    // 滑动状态不显示
                    fabDailyScoreBackToTop.setVisibility(View.GONE);
                }
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

    @OnClick(R.id.fab_daily_score_back_to_top)
    public void dailyScoreBackToTop() {
        rvDailyScore.smoothScrollToPosition(0);
        fabDailyScoreBackToTop.setVisibility(View.GONE);
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
