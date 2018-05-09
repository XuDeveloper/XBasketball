package com.xu.xbasketball.ui.news.activity;

import com.xu.xbasketball.R;
import com.xu.xbasketball.base.BaseMVPActivity;
import com.xu.xbasketball.base.contract.news.NewsDetailContract;
import com.xu.xbasketball.model.bean.HupuNewsDetailBean;
import com.xu.xbasketball.presenter.news.NewsDetailPresenter;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class NewsDetailActivity extends BaseMVPActivity<NewsDetailPresenter> implements NewsDetailContract.View {

    @Override
    public int getLayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initData() {

    }

    @Override
    public void showNewsDetail(HupuNewsDetailBean hupuNewsDetailBean) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
