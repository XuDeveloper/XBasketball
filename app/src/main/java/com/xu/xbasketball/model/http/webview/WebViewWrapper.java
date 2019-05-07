package com.xu.xbasketball.model.http.webview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by xu on 2019/05/04.
 *
 * @author xu
 */
public class WebViewWrapper extends WebView {

    public WebViewWrapper(Context context) {
        this(context, null, -1, -1);
    }

    public WebViewWrapper(Context context, AttributeSet attrs) {
        this(context, attrs, -1, -1);
    }

    public WebViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, -1);
    }

    public WebViewWrapper(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {
        WebViewHelper.setDefaultSetting(this);
        WebViewHelper.removeJavascriptInterfaces(this);
    }

    public void openWebViewDebug() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public void enableJavascript() {
        getSettings().setJavaScriptEnabled(true);
    }

    public void disableJavascript() {
        getSettings().setJavaScriptEnabled(false);
    }

    public void destory() {
        loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
        clearHistory();
        ((ViewGroup) this.getParent()).removeView(this);
        destroy();
    }

}
