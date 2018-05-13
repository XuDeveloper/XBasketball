package com.xu.xbasketball.ui.news.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseMVPActivity;
import com.xu.xbasketball.base.contract.news.NewsDetailContract;
import com.xu.xbasketball.model.bean.HupuNewsDetailBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.news.NewsDetailPresenter;

import butterknife.BindView;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class NewsDetailActivity extends BaseMVPActivity<NewsDetailPresenter> implements NewsDetailContract.View {

    @BindView(R.id.btn_news_detail_back)
    Button btnNewsDetailBack;
    @BindView(R.id.loading)
    ViewStub loading;
    @BindView(R.id.iv_news_detail_pic)
    ImageView ivNewsDetailPic;
    @BindView(R.id.tv_news_detail)
    TextView tvNewsDetail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        loading.inflate();
        String nid = getIntent().getStringExtra(Constants.NEWS_NID);
        if (nid != null) {
            mPresenter.getNewsDetail(Constants.CLIENT, nid);
        }
    }

    @Override
    public void showNewsDetail(HupuNewsDetailBean hupuNewsDetailBean) {
        ImageLoader.load(this, hupuNewsDetailBean.getImg_m(), ivNewsDetailPic);
        tvNewsDetail.setText(hupuNewsDetailBean.getContent());
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

    public static void launch(Context context, String nid) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_NID, nid);
        context.startActivity(intent);
    }
}
