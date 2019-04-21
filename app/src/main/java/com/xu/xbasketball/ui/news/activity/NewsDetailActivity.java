package com.xu.xbasketball.ui.news.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.utils.EspressoIdlingResource;
import com.xu.xbasketball.utils.JavascriptUtil;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import butterknife.BindView;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class NewsDetailActivity extends BaseActivity {

    @BindView(R.id.wv_news_detail)
    WebView wvNewsDetail;
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
        // for Espresso Test
        EspressoIdlingResource.increment();

        String url = getIntent().getStringExtra(Constants.NEWS_URL);
        url = url.split("\\?")[0];
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 从Android5.0开始，WebView默认不支持同时加载Https和Http混合模式
            if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
        }
        // 开启webview 调试模式
//        WebView.setWebContentsDebuggingEnabled(true);
        wvNewsDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(JavascriptUtil.getNewsDetailJsCode()[0]);
                view.loadUrl(JavascriptUtil.getNewsDetailJsCode()[1]);
                // for Espresso Test
                if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                    EspressoIdlingResource.decrement();
                }
                super.onPageFinished(view, url);
            }
        });
        wvNewsDetail.loadUrl(url);
    }

    @Override
    protected void onPause() {
        wvNewsDetail.onPause();
        wvNewsDetail.pauseTimers();
        super.onPause();
    }

    @Override
    protected void onResume() {
        wvNewsDetail.onResume();
        wvNewsDetail.resumeTimers();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (wvNewsDetail != null) {
            wvNewsDetail.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            wvNewsDetail.clearHistory();
            ((ViewGroup) wvNewsDetail.getParent()).removeView(wvNewsDetail);
            wvNewsDetail.destroy();
            wvNewsDetail = null;
        }
        super.onDestroy();
        finishAfterTransition();
    }

    public static void launch(Context context, String url, String img, String title, Bundle bundle) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        intent.putExtra(Constants.NEWS_URL, url);
        intent.putExtra(Constants.NEWS_IMG, img);
        intent.putExtra(Constants.NEWS_TITLE, title);
        if (bundle != null) {
            context.startActivity(intent, bundle);
        } else {
            context.startActivity(intent);
        }
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

}
