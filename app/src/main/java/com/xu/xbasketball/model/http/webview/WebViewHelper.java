package com.xu.xbasketball.model.http.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xu.xbasketball.app.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;


/**
 * Created by xu on 2019/04/21.
 *
 * @author xu
 */
public class WebViewHelper {

    @SuppressLint("SetJavaScriptEnabled")
    public static void setDefaultSetting(WebView webView) {
        WebSettings webSettings = webView.getSettings();
        // 允许js代码
        webSettings.setJavaScriptEnabled(true);
        // 设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合WebView的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        // 禁用缩放
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(false);
        // 允许SessionStorage/LocalStorage存储
        webSettings.setDomStorageEnabled(true);
        // 5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 屏蔽长按事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        // 禁用文字缩放
        webSettings.setTextZoom(100);
        // 10M缓存，api 18后，系统自动管理。
//        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        // 允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(Constants.PATH_WEBVIEW_CACHE);
        // 允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        // 设置UA
        webSettings.setUserAgentString(webSettings.getUserAgentString() + " xbasketball/");
        // 自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void removeJavascriptInterfaces(WebView webView) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB &&
                    Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            }
        } catch (Throwable tr) {
            tr.printStackTrace();
        }
    }

    public static WebResourceResponse getLocalResponse(Context context, String url) {
        Set<String> set = WebViewInterceptResource.getKeySet();
        for (String interceptUrl : set) {
            if (url.contains(interceptUrl)) {
                InputStream is;
                try {
                    // Map local封装为对象？
                    LocalResource resource = WebViewInterceptResource.get(interceptUrl);
                    is = context.getApplicationContext().getAssets().open(resource.getLocalPath());
                    return new WebResourceResponse(resource.getMimeType(),
                            "utf-8", is);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
