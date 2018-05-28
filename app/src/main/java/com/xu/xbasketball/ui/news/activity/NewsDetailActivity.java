package com.xu.xbasketball.ui.news.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseMVPActivity;
import com.xu.xbasketball.base.contract.news.NewsDetailContract;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.news.NewsDetailPresenter;

import butterknife.BindView;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class NewsDetailActivity extends BaseMVPActivity<NewsDetailPresenter> implements NewsDetailContract.View {

//    @BindView(R.id.btn_news_detail_back)
//    Button btnNewsDetailBack;
    @BindView(R.id.loading)
    ViewStub loading;
    @BindView(R.id.iv_news_detail_pic)
    ImageView ivNewsDetailPic;
//    @BindView(R.id.tv_news_detail_title)
//    TextView tvNewsDetailTitle;
//    @BindView(R.id.tv_news_detail)
//    TextView tvNewsDetail;

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
        String url = getIntent().getStringExtra(Constants.NEWS_URL);
        if (url != null) {
//            mPresenter.getNewsDetail(Constants.CLIENT, nid);
        }
        String img = getIntent().getStringExtra(Constants.NEWS_IMG);
        if (img != null) {
            ImageLoader.load(this, img, ivNewsDetailPic);
        }
    }

    @Override
    public void showNewsDetail() {
//        ImageLoader.load(this, hupuNewsDetailBean.getImg_m(), ivNewsDetailPic);
//        tvNewsDetail.setText(hupuNewsDetailBean.getContent());
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

//    @OnClick(R.id.btn_news_detail_back)
//    public void btn_back() {
//        finish();
//    }

    public static void launch(Context context, String url, String img) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_URL, url);
        intent.putExtra(Constants.NEWS_IMG, img);
        context.startActivity(intent);
    }
}
