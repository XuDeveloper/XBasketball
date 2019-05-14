package com.xu.xbasketball.ui.video.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.http.webview.WebViewWrapper;
import com.xu.xbasketball.utils.EspressoIdlingResource;
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
    WebViewWrapper wvVideoDetail;

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
        // for Espresso Test
        EspressoIdlingResource.increment();

        setToolBar(toolbar, "");
        videoTitle = getIntent().getStringExtra(Constants.VIDEO_TITLE);
        videoId = getIntent().getStringExtra(Constants.VIDEO_ID);
        if (!TextUtils.isEmpty(videoId)) {
            videoUrl = "https://xw.qq.com/a/video/" + videoId;
        }
        wvVideoDetail.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 从Android5.0开始，WebView默认不支持同时加载Https和Http混合模式
            wvVideoDetail.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 开启webview调试模式
//        wvVideoDetail.openWebViewDebug();
//        WebView.setWebContentsDebuggingEnabled(true);
        wvVideoDetail.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(JavascriptUtil.getVideoDetailJsCode()[0]);
                view.loadUrl(JavascriptUtil.getVideoDetailJsCode()[1]);
                super.onPageFinished(view, url);
            }
        });
        wvVideoDetail.load(videoUrl);
        // for Espresso Test
        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }
    }

    @Override
    protected void onPause() {
        wvVideoDetail.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        wvVideoDetail.resume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (wvVideoDetail != null) {
            wvVideoDetail.destroyWebView();
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

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }

}
