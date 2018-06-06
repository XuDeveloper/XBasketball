package com.xu.xbasketball.ui.pic.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseMVPFragment;
import com.xu.xbasketball.base.contract.pic.PicContract;
import com.xu.xbasketball.model.bean.SinaPicBean;
import com.xu.xbasketball.presenter.pic.PicPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by zhaoxuzhang on 2018/6/6.
 *
 * @author zhaoxuzhang
 */
public class PicFragment extends BaseMVPFragment<PicPresenter> implements PicContract.View {


    @BindView(R.id.rv_pic)
    RecyclerView rvPic;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_pic;
    }

    @Override
    public void showPics(List<SinaPicBean> pics) {

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
