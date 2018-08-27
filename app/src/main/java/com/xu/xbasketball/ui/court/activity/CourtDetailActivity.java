package com.xu.xbasketball.ui.court.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;


import butterknife.BindView;

/**
 * Created by Xu on 2018/5/6.
 *
 * @author Xu
 */
public class CourtDetailActivity extends BaseActivity {

    @BindView(R.id.wv_court_detail)
    WebView wvCourtDetail;
    @BindView(R.id.loading)
    ViewStub loading;
    @BindView(R.id.clp_toolbar)
    CollapsingToolbarLayout clpToolbar;
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
        String title = getIntent().getStringExtra(Constants.COURT_TITLE);
        if (title != null) {
            clpToolbar.setTitle(title);
        }
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
        wvCourtDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        if (url != null) {
            wvCourtDetail.loadUrl(url);
        }
    }

    public static void launch(Context context, String url, String title) {
        Intent intent = new Intent(context, CourtDetailActivity.class);
        intent.putExtra(Constants.COURT_URL, url);
        intent.putExtra(Constants.COURT_TITLE, title);
        context.startActivity(intent);
    }
}
