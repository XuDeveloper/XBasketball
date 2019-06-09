package com.xu.xbasketball.ui.video.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;
import com.xu.xbasketball.app.Constants;
import com.xu.xbasketball.base.BaseActivity;
import com.xu.xbasketball.model.http.webview.WebViewHelper;
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
    @BindView(R.id.fl_video_detail)
    FrameLayout flVideoDetail;

    private String videoTitle;
    private String videoId;
    private String videoUrl;

    WebViewWrapper wvVideoDetail;

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

        videoTitle = getIntent().getStringExtra(Constants.VIDEO_TITLE);
        setToolBar(toolbar, videoTitle);
        videoId = getIntent().getStringExtra(Constants.VIDEO_ID);
        if (!TextUtils.isEmpty(videoId)) {
            videoUrl = "https://xw.qq.com/a/video/" + videoId;
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wvVideoDetail = App.getInstance().getGlobalWebview();
        flVideoDetail.addView(wvVideoDetail);

        wvVideoDetail.init();

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
                // // https://mat1.gtimg.com/www/mobi/2017/image/nshare-tl.svg
                //        resourceMap.put("https://mat1.gtimg.com/www/mobi/2017/image/nshare-tl.svg",
                //                new LocalResource("image/*", "video/image/nshare-tl.svg"));
//                Log.i("WebView_Load", "// " + request.getUrl().toString());
//                Log.i("WebView_Load", "resourceMap.put(\"" + request.getUrl().toString() + "\", \n");
//                Log.i("WebView_Load", "new LocalResource(\"image/*\", \"video/image/" + request.getUrl().toString().split("/")[request.getUrl().toString().split("/").length - 1] + "\"));");
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
            wvVideoDetail.clearWebView();
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
