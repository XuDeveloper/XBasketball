package com.xu.xbasketball.ui.news.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseMVPActivity;
import com.xu.xbasketball.base.contract.news.NewsDetailContract;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.news.NewsDetailPresenter;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class NewsDetailActivity extends BaseMVPActivity<NewsDetailPresenter> implements NewsDetailContract.View {

    @BindView(R.id.wv_news_detail)
    WebView wvNewsDetail;
    @BindView(R.id.loading)
    ViewStub loading;
    @BindView(R.id.iv_news_detail_pic)
    ImageView ivNewsDetailPic;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        String url = getIntent().getStringExtra(Constants.NEWS_URL);
        if (url != null) {
//            mPresenter.getNewsDetail(Constants.CLIENT, nid);
        }
        String img = getIntent().getStringExtra(Constants.NEWS_IMG);
        if (img != null) {
            ImageLoader.load(this, img, ivNewsDetailPic);
        }
        String title = getIntent().getStringExtra(Constants.NEWS_TITLE);
        if (title != null) {
            clpToolbar.setTitle(title);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebSettings settings = wvNewsDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        wvNewsDetail.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvNewsDetail.loadUrl(url);
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

    public static void launch(Context context, String url, String img, String title) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_URL, url);
        intent.putExtra(Constants.NEWS_IMG, img);
        intent.putExtra(Constants.NEWS_TITLE, title);
        context.startActivity(intent);
    }
}
