package com.xu.xbasketball.model.http.webview;

import android.content.Context;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * Created by xu on 2019/04/21.
 *
 * @author xu
 */
public class WebViewHelper {

    public static void init(Context context) {
        //x5内核初始化接口
        QbSdk.initX5Environment(context, null);
    }

    public static void setDefaultSetting(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setSupportZoom(true);
        webSettings.setDomStorageEnabled(true);
    }

}
