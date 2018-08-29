package com.xu.xbasketball.ui.court.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseLazyLoadFragment;
import com.xu.xbasketball.base.contract.court.HupuCourtContract;
import com.xu.xbasketball.model.bean.HupuCourtBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.court.HupuCourtPresenter;
import com.xu.xbasketball.ui.court.activity.CourtDetailActivity;
import com.xu.xbasketball.ui.court.adapter.CourtAdapter;
import com.xu.xbasketball.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xu on 2018/8/24.
 */
public class CourtFragment extends BaseLazyLoadFragment<HupuCourtPresenter> implements HupuCourtContract.View {

    @BindView(R.id.rv_court)
    RecyclerView rvCourt;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private CourtAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    private List<HupuCourtBean> mList;

    private int page;
    private boolean isLoadingMore = false;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initDatas() {
        mList = new ArrayList<>();
        adapter = new CourtAdapter(mContext);
        page = 1;
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        mLayoutManager = new LinearLayoutManager(mContext);
        rvCourt.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        rvCourt.setHasFixedSize(true);
        rvCourt.setLayoutManager(mLayoutManager);
        rvCourt.setItemAnimator(new DefaultItemAnimator());
        rvCourt.setAdapter(adapter);

        adapter.setOnItemClickListener(new CourtAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                CourtDetailActivity.launch(getContext(), "https:" + mList.get(position).getUrl());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_court;
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getCourtArticles(page);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mList.clear();
                mPresenter.getCourtArticles(page);
            }
        });

        rvCourt.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = mLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem >= adapter.getItemCount() - 1 && !isLoadingMore) {
                    isLoadingMore = true;
                    page++;
                    mPresenter.getCourtArticles(page);
                }
            }
        });
    }

    @Override
    public void showCourtArticles(List<HupuCourtBean> data) {
        mList.addAll(data);
        adapter.updateData(mList);
        isLoadingMore = false;
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
