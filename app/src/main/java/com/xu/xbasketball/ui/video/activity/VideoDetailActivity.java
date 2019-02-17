package com.xu.xbasketball.ui.video.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.utils.JavascriptUtil;

import butterknife.BindView;

/**
 * Created by xu on 2018/12/09.
 *
 * @author xu
 */
public class VideoDetailActivity extends BaseActivity {

    @BindView(R.id.tb_basketball)
    Toolbar toolbar;
    @BindView(R.id.wv_video_detail)
    WebView wvVideoDetail;

    private String videoTitle;
    private String videoId;
    private String videoUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_video_detail;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        setToolBar(toolbar, "");
        videoTitle = getIntent().getStringExtra(Constants.VIDEO_TITLE);
        videoId = getIntent().getStringExtra(Constants.VIDEO_ID);
        videoUrl = "https://xw.qq.com/a/video/" + videoId;
        WebSettings settings = wvVideoDetail.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 从Android5.0开始，WebView默认不支持同时加载Https和Http混合模式
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 开启webview 调试模式
        WebView.setWebContentsDebuggingEnabled(true);
        wvVideoDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                view.loadUrl(JavascriptUtil.getNewsDetailJsCode()[0]);
//                view.loadUrl(JavascriptUtil.getNewsDetailJsCode()[1]);
                super.onPageFinished(view, url);
            }
        });
        wvVideoDetail.loadUrl(videoUrl);
    }

    @Override
    protected void onPause() {
        wvVideoDetail.onPause();
        wvVideoDetail.pauseTimers();
        super.onPause();
    }

    @Override
    protected void onResume() {
        wvVideoDetail.onResume();
        wvVideoDetail.resumeTimers();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (wvVideoDetail != null) {
            wvVideoDetail.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            wvVideoDetail.clearHistory();
            ((ViewGroup) wvVideoDetail.getParent()).removeView(wvVideoDetail);
            wvVideoDetail.destroy();
            wvVideoDetail = null;
        }
        super.onDestroy();
        finishAfterTransition();
    }

    public static void launch(Context context, String video_id, String title) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.putExtra(Constants.VIDEO_ID, video_id);
        intent.putExtra(Constants.VIDEO_TITLE, title);
        context.startActivity(intent);
    }

}
