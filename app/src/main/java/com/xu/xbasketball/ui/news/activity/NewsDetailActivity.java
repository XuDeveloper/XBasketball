package com.xu.xbasketball.ui.news.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.Toolbar;
import androidx.test.espresso.IdlingResource;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.http.webview.WebViewHelper;
import com.xu.xbasketball.model.http.webview.WebViewWrapper;
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

    @BindView(R.id.fl_news_detail)
    FrameLayout flNewsDetail;
    @BindView(R.id.iv_news_detail_pic)
    ImageView ivNewsDetailPic;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    WebViewWrapper wvNewsDetail;

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

        wvNewsDetail = App.getInstance().getGlobalWebview();
        flNewsDetail.addView(wvNewsDetail);

        wvNewsDetail.init();

        // 无图模式
        if (App.getAppComponent().preferencesHelper().getNoImageState()) {
            wvNewsDetail.getSettings().setBlockNetworkImage(true);
        } else {
            wvNewsDetail.getSettings().setBlockNetworkImage(false);
        }
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

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                // 如果命中本地资源，使用本地资源代替
                Log.i("WebView_Load_Old_Method", "url: " + url);
                if (WebViewHelper.hasLocalResource(url)) {
                    WebResourceResponse response = WebViewHelper.getLocalResponse(mContext, url);
                    if (response != null) {
                        Log.i("WebView_Load_Old_Method", "intercept!");
                        return response;
                    }
                }
                return super.shouldInterceptRequest(view, url);
            }

            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                // 如果命中本地资源，使用本地资源代替
                Log.i("WebView_Load", "url: " + request.getUrl().toString());
                Log.i("WebView_Load", "request: " + request.getRequestHeaders().toString());
                String url = request.getUrl().toString();
                if (WebViewHelper.hasLocalResource(url)) {
                    WebResourceResponse response = WebViewHelper.getLocalResponse(mContext, request.getUrl().toString());
                    if (response != null) {
                        Log.i("WebView_Load", "intercept!");
                        return response;
                    }
                }
                return super.shouldInterceptRequest(view, request);
            }
        });
        wvNewsDetail.load(url);
    }

    @Override
    protected void onPause() {
        wvNewsDetail.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        wvNewsDetail.resume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (wvNewsDetail != null) {
            wvNewsDetail.clearWebView();
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
