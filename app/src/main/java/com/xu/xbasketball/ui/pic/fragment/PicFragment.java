package com.xu.xbasketball.ui.pic.fragment;

import android.view.View;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseMVPFragment;
import com.xu.xbasketball.base.contract.pic.PicContract;
import com.xu.xbasketball.model.bean.SinaPicBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.pic.PicPresenter;
import com.xu.xbasketball.ui.pic.activity.PicDetailActivity;
import com.xu.xbasketball.ui.pic.adapter.PicAdapter;
import com.xu.xbasketball.utils.SnackBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.fab_pic_back_to_top)
    FloatingActionButton fabPicBackToTop;

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
        fabPicBackToTop.hide();
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
                int lastItem = Math.max(visibleItems[0], visibleItems[1]);
                if (lastItem >= adapter.getItemCount() - 1 && !isLoadingMore && dy > 0) {
                    isLoadingMore = true;
                    page++;
                    mPresenter.getPics(page, num);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int[] firstVisibleItems = mStaggeredGridLayoutManager.findFirstVisibleItemPositions(null);
                int firstVisibleItemPosition = Math.min(firstVisibleItems[0], firstVisibleItems[1]);
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        ImageLoader.resumeAllImageRequests(mContext);
                        if (firstVisibleItemPosition == 0) {
                            fabPicBackToTop.hide();
                        } else {
                            fabPicBackToTop.show();
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        ImageLoader.pauseAllImageRequests(mContext);
                        fabPicBackToTop.hide();
                        break;
                }
            }
        });

        adapter.setOnItemClickListener(new PicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View shareView) {
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, shareView, "shareView");
                PicDetailActivity.launch(mContext, mList.get(position).getImg_url(), options.toBundle());
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

    @OnClick(R.id.fab_pic_back_to_top)
    public void picBackToTop() {
        rvPic.smoothScrollToPosition(0);
        fabPicBackToTop.hide();
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
