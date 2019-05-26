package com.xu.xbasketball.ui.court.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseMVPActivity;
import com.xu.xbasketball.base.contract.court.HupuCourtDetailContract;
import com.xu.xbasketball.model.bean.HupuCourtDetailBean;
import com.xu.xbasketball.model.http.webview.WebViewHelper;
import com.xu.xbasketball.model.http.webview.WebViewWrapper;
import com.xu.xbasketball.model.img.ILoadingImg;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.court.HupuCourtDetailPresenter;
import com.xu.xbasketball.utils.EspressoIdlingResource;
import com.xu.xbasketball.utils.JavascriptUtil;
import com.xu.xbasketball.utils.SnackBarUtil;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class CourtDetailActivity extends BaseMVPActivity<HupuCourtDetailPresenter> implements HupuCourtDetailContract.View {

    @BindView(R.id.wv_court_detail)
    WebViewWrapper wvCourtDetail;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.iv_court_detail_pic)
    ImageView ivCourtDetailPic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private boolean isJsCodeLoaded = false;

    private long startTime;
    private long endTime;

    @Override
    public int getLayoutId() {
        return R.layout.activity_court_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        // for Espresso Test
        EspressoIdlingResource.increment();
        String url = getIntent().getStringExtra(Constants.COURT_URL);
        String detailAddress = url.split("/")[url.split("/").length - 1];

        mPresenter.getCourtArticleDetail(detailAddress);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        startTime = System.currentTimeMillis();
        wvCourtDetail.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 从Android5.0开始，WebView默认不支持同时加载Https和Http混合模式
            if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
                wvCourtDetail.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
        }
        wvCourtDetail.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                hideProgress();
                endTime = System.currentTimeMillis();
                Log.i("WebView_Load", "cost time: " + (endTime - startTime) + "ms");
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
        wvCourtDetail.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                showProgress();
                if (newProgress > 70 && !isJsCodeLoaded) {
                    view.loadUrl(JavascriptUtil.getCourtDetailJsCode()[0]);
                    view.loadUrl(JavascriptUtil.getCourtDetailJsCode()[1]);
                    isJsCodeLoaded = true;
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        wvCourtDetail.load(url);
    }

    @Override
    public void showCourtArticleDetail(HupuCourtDetailBean data) {
        if (!data.getTitle().equals("")) {
            clpToolbar.setTitle(data.getTitle());
        } else {
            clpToolbar.setTitle("无标题");
            SnackBarUtil.show(view, "文章错误，2s后退出!");
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    finish();
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, 2000);
        }
        if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
            if (!data.getImg().equals("")) {
                ImageLoader.load(this, data.getImg(), ivCourtDetailPic, null, new ILoadingImg() {
                    @Override
                    public void onResourceReady(Bitmap bitmap) {

                    }

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onFail() {

                    }

                    @Override
                    public void onClear() {

                    }
                });
            }
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        // for Espresso Test
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }
    }

    @Override
    public void showLoadFailMsg(String msg) {
        SnackBarUtil.show(view, msg);
    }

    @Override
    protected void onPause() {
        wvCourtDetail.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        wvCourtDetail.stop();
        super.onStop();
    }

    @Override
    protected void onResume() {
        wvCourtDetail.resume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (wvCourtDetail != null) {
            wvCourtDetail.destroyWebView();
        }
        super.onDestroy();
    }

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, CourtDetailActivity.class);
        intent.putExtra(Constants.COURT_URL, url);
        context.startActivity(intent);
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

}
