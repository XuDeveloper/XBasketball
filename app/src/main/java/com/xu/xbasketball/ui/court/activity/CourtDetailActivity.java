package com.xu.xbasketball.ui.court.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseMVPActivity;
import com.xu.xbasketball.base.contract.court.HupuCourtDetailContract;
import com.xu.xbasketball.model.bean.HupuCourtDetailBean;
import com.xu.xbasketball.model.img.ImageLoader;
import com.xu.xbasketball.presenter.court.HupuCourtDetailPresenter;
import com.xu.xbasketball.utils.JavascriptUtil;
import com.xu.xbasketball.utils.SnackBarUtil;

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
    @BindView(R.id.loading)
    ViewStub loading;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
    @BindView(R.id.iv_court_detail_pic)
    ImageView ivCourtDetailPic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

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
        String url = getIntent().getStringExtra(Constants.COURT_URL);

        String detail = url.split("/")[url.split("/").length - 1];

        mPresenter.getCourtArticleDetail(detail);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        WebSettings settings = wvCourtDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        wvCourtDetail.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

        });
        wvCourtDetail.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                showProgress();
                if (newProgress > 70) {
                    view.loadUrl(JavascriptUtil.getCourtDetailJsCode()[0]);
                    view.loadUrl(JavascriptUtil.getCourtDetailJsCode()[1]);
                }
                if (newProgress == 100) {
                    hideProgress();
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
        if (!data.getImg().equals("")) {
            ImageLoader.load(this, data.getImg(), ivCourtDetailPic);
        } else {
            ImageLoader.loadMipmap(this, R.mipmap.ic_launcher, ivCourtDetailPic);
        }
    }

    @Override
    public void showProgress() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        loading.setVisibility(View.GONE);
    }

    public static void launch(Context context, String url) {
        Intent intent = new Intent(context, CourtDetailActivity.class);
        intent.putExtra(Constants.COURT_URL, url);
        context.startActivity(intent);
    }

}
