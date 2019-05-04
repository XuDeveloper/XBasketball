package com.xu.xbasketball.ui.court.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
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
    WebView wvCourtDetail;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.iv_court_detail_pic)
    ImageView ivCourtDetailPic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private boolean isJsCodeLoaded = false;

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

        WebViewHelper.setDefaultSetting(wvCourtDetail);
        WebViewHelper.removeJavascriptInterfaces(wvCourtDetail);
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
        wvCourtDetail.loadUrl(url);
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
        wvCourtDetail.onPause();
        wvCourtDetail.pauseTimers();
        super.onPause();
    }

    @Override
    protected void onStop() {
        wvCourtDetail.getSettings().setJavaScriptEnabled(false);
        super.onStop();
    }

    @Override
    protected void onResume() {
        wvCourtDetail.onResume();
        wvCourtDetail.resumeTimers();
        wvCourtDetail.getSettings().setJavaScriptEnabled(true);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (wvCourtDetail != null) {
            wvCourtDetail.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            wvCourtDetail.clearHistory();
            ((ViewGroup) wvCourtDetail.getParent()).removeView(wvCourtDetail);
            wvCourtDetail.destroy();
            wvCourtDetail = null;
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
