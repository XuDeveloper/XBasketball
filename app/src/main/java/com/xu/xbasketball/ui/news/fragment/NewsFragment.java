package com.xu.xbasketball.ui.news.fragment;

import android.app.ActivityOptions;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseLazyLoadFragment;
import com.xu.xbasketball.base.contract.news.NewsContract;
import com.xu.xbasketball.model.bean.TencentNewsBean;
import com.xu.xbasketball.presenter.news.NewsPresenter;
import com.xu.xbasketball.ui.news.activity.NewsDetailActivity;
import com.xu.xbasketball.ui.news.adapter.NewsAdapter;
import com.xu.xbasketball.ui.pic.activity.PicDetailActivity;
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

    private boolean isLoadingMore = false;

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

        adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View shareImg, View shareContent) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity,
                        Pair.create(shareImg, "shareImg"),
                        Pair.create(shareContent, "shareContent"));
                NewsDetailActivity.launch(getContext(), mList.get(position).getUrl(),
                        mList.get(position).getBigImage().get(0) + ".jpg",
                        mList.get(position).getTitle(), options.toBundle());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void lazyLoad() {
        mPresenter.getNews(Constants.DEVID);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                mPresenter.getNews(Constants.DEVID);
            }
        });

        rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = mLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem >= adapter.getItemCount() - 1 && !isLoadingMore) {
                    isLoadingMore = true;
                    mPresenter.getNews(Constants.DEVID);
                }
            }
        });

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getNews(Constants.DEVID);
            }
        });
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showNews(List<TencentNewsBean> news) {
        mList.addAll(news);
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
