package com.xu.xbasketball.ui.video.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseLazyLoadFragment;
import com.xu.xbasketball.base.BaseMVPFragment;
import com.xu.xbasketball.base.contract.news.NewsContract;
import com.xu.xbasketball.base.contract.video.VideoContract;
import com.xu.xbasketball.model.bean.TencentNewsBean;
import com.xu.xbasketball.model.bean.TencentVideoBean;
import com.xu.xbasketball.presenter.news.NewsPresenter;
import com.xu.xbasketball.presenter.video.VideoPresenter;
import com.xu.xbasketball.ui.news.activity.NewsDetailActivity;
import com.xu.xbasketball.ui.video.activity.VideoDetailActivity;
import com.xu.xbasketball.ui.video.adapter.VideoAdapter;
import com.xu.xbasketball.utils.SnackBarUtil;
import com.xu.xbasketball.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Xu on 2018/10/7.
 *
 * @author Xu
 */
public class VideoFragment extends BaseMVPFragment<VideoPresenter> implements VideoContract.View {

    @BindView(R.id.rv_video)
    RecyclerView rvVideo;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    @BindView(R.id.fab_video_back_to_top)
    FloatingActionButton fabVideoBackToTop;

    private VideoAdapter adapter;
    private LinearLayoutManager mLayoutManager;

    private List<TencentVideoBean> mList;

    private int page;
    private boolean isLoadingMore = false;

    @Override
    public void initData() {
        fabVideoBackToTop.setVisibility(View.GONE);
        mList = new ArrayList<>();
        adapter = new VideoAdapter(mContext);
        page = 1;

        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        mLayoutManager = new LinearLayoutManager(mContext);
        rvVideo.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        rvVideo.setHasFixedSize(true);
        rvVideo.setLayoutManager(mLayoutManager);
        rvVideo.setItemAnimator(new DefaultItemAnimator());
        rvVideo.setAdapter(adapter);

        adapter.setOnItemClickListener((position, shareView) -> {
            VideoDetailActivity.launch(mContext, mList.get(position).getId(),
                    mList.get(position).getTitle());
        });

        mPresenter.getVideos(page);
        swipeRefresh.setOnRefreshListener(() -> {
            page = 1;
            isLoadingMore = false;
            mList.clear();
            mPresenter.getVideos(page);
        });

        rvVideo.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastItem = mLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastItem >= adapter.getItemCount() - 1 && !isLoadingMore && dy > 0) {
                    isLoadingMore = true;
                    page++;
                    mPresenter.getVideos(page);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 判断是否超过一屏
                    if (firstVisibleItemPosition == 0) {
                        fabVideoBackToTop.setVisibility(View.GONE);
                    } else {
                        fabVideoBackToTop.setVisibility(View.VISIBLE);
                    }
                } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    // 滑动状态不显示
                    fabVideoBackToTop.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void showVideos(List<TencentVideoBean> videos) {
        mList.addAll(videos);
        adapter.updateData(mList);
        isLoadingMore = false;
    }

    @OnClick(R.id.fab_video_back_to_top)
    public void videoBackToTop() {
        rvVideo.smoothScrollToPosition(0);
        fabVideoBackToTop.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showLoadFailMsg(String msg) {
        SnackBarUtil.show(view, msg);
    }
}
