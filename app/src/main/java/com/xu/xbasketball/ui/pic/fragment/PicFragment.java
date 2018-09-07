package com.xu.xbasketball.ui.pic.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseMVPFragment;
import com.xu.xbasketball.base.contract.pic.PicContract;
import com.xu.xbasketball.model.bean.SinaPicBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.pic.PicPresenter;
import com.xu.xbasketball.ui.pic.activity.PicDetailActivity;
import com.xu.xbasketball.ui.pic.adapter.PicAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Xu on 2018/6/6.
 *
 * @author Xu
 */
public class PicFragment extends BaseMVPFragment<PicPresenter> implements PicContract.View {

    private static final int SPAN_COUNT = 2;

    @BindView(R.id.rv_pic)
    RecyclerView rvPic;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private PicAdapter adapter;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private List<SinaPicBean> mList;

    private int page;
    private int num;
    private boolean isLoadingMore = false;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        adapter = new PicAdapter(mContext);
        page = 1;
        num = 40;
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL);
        mStaggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mStaggeredGridLayoutManager.setItemPrefetchEnabled(false);
        rvPic.setLayoutManager(mStaggeredGridLayoutManager);
        rvPic.setAdapter(adapter);

        mPresenter.getPics(page, num);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isLoadingMore = false;
                mList.clear();
                mPresenter.getPics(page, num);
            }
        });

        rvPic.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = mStaggeredGridLayoutManager.findLastVisibleItemPositions(null);
                int lastItem = Math.max(visibleItems[0],visibleItems[1]);
                if (lastItem >= adapter.getItemCount() - 1 && !isLoadingMore && dy > 0) {
                    isLoadingMore = true;
                    page++;
                    mPresenter.getPics(page, num);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        ImageLoader.resumeImageRequests(mContext);
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        ImageLoader.pauseImageRequests(mContext);
                        break;
                }
            }
        });

        adapter.setOnItemClickListener(new PicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PicDetailActivity.launch(mContext, mList.get(position).getImg_url());
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pic;
    }

    @Override
    public void showPics(List<SinaPicBean> pics) {
        mList.addAll(pics);
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
