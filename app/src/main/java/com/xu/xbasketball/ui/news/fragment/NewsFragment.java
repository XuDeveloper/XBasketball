package com.xu.xbasketball.ui.news.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseLazyLoadFragment;
import com.xu.xbasketball.base.contract.news.NewsContract;
import com.xu.xbasketball.model.bean.TencentNewsBean;
import com.xu.xbasketball.presenter.news.NewsPresenter;
import com.xu.xbasketball.ui.news.adapter.NewsAdapter;
import com.xu.xbasketball.widget.DividerItemDecoration;

import java.util.ArrayList;
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

    private List<TencentNewsBean> mList;

    @Override
    protected void initDatas() {
        mList = new ArrayList<>();
        adapter = new NewsAdapter(mContext);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        mLayoutManager = new LinearLayoutManager(mContext);
        rvNews.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        rvNews.setHasFixedSize(true);
        rvNews.setLayoutManager(mLayoutManager);
        rvNews.setItemAnimator(new DefaultItemAnimator());
        rvNews.setAdapter(adapter);

//        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                NewsDetailActivity.launch(getContext(), mList.get(position).getNid());
//            }
//        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getNews((System.currentTimeMillis() + 30000) + "");
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNews((System.currentTimeMillis() + 30000) + "");
            }
        });
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showNews(List<TencentNewsBean> news) {
        mList.clear();
        mList.addAll(news);
        adapter.updateData(mList);
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
