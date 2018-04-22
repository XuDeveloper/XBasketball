package com.xu.xbasketball.ui.news.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseLazyLoadFragment;
import com.xu.xbasketball.base.contract.news.NewsContract;
import com.xu.xbasketball.model.bean.HupuNewsBean;
import com.xu.xbasketball.presenter.news.NewsPresenter;
import com.xu.xbasketball.ui.news.adapter.NewsAdapter;
import com.xu.xbasketball.widget.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Xu on 2018/4/7.
 *
 * @author Xu
 */
public class NewsFragment extends BaseLazyLoadFragment<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private NewsAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void initDatas() {
        adapter = new NewsAdapter(mContext);
        mLayoutManager = new LinearLayoutManager(mContext);
        rvNews.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(mLayoutManager);
        rvNews.setItemAnimator(new DefaultItemAnimator());
        rvNews.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getNews(Constants.CLIENT);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNews(Constants.CLIENT);
            }
        });
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showNews(List<HupuNewsBean> news) {
        adapter.updateData(news);
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
