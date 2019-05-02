package com.xu.xbasketball.model.http.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xu.xbasketball.app.Constants;


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
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        // 允许SessionStorage/LocalStorage存储
        webSettings.setDomStorageEnabled(true);
        // 5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        // 禁用缩放
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(false);
        // 禁用文字缩放
        webSettings.setTextZoom(100);
        // 10M缓存，api 18后，系统自动管理。
//        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        // 允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(Constants.PATH_WEBVIEW_CACHE);
        // 允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        // 不保存密码
        webSettings.setSavePassword(false);
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


}
