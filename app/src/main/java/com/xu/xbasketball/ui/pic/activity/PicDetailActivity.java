package com.xu.xbasketball.ui.pic.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.img.ImageLoader;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;

/**
 * Created by Xu on 2018/9/1.
 *
 * @author Xu
 */
public class PicDetailActivity extends BaseActivity {

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
        return R.layout.activity_pic_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        String url = getIntent().getStringExtra(Constants.NEWS_URL);
        if (url != null) {
            Log.i("Test", url);
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
        settings.setDomStorageEnabled(true);
        wvNewsDetail.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wvNewsDetail.loadUrl(url);
    }

    public static void launch(Context context, String url, String img, String title) {
        Intent intent = new Intent(context, PicDetailActivity.class);
        intent.putExtra(Constants.NEWS_URL, url);
        intent.putExtra(Constants.NEWS_IMG, img);
        intent.putExtra(Constants.NEWS_TITLE, title);
        context.startActivity(intent);
    }
}
