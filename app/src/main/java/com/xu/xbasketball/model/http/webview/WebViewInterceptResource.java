package com.xu.xbasketball.model.http.webview;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by xu on 2019-05-21.
 */
public class WebViewInterceptResource {

    private static Map<String, LocalResource> resourceMap;

    static {
        resourceMap = new HashMap<>();
        // hupu court
        // https://b3.hoopchina.com.cn/web/module/dace/m/m_dace.js
        resourceMap.put("https://b3.hoopchina.com.cn/web/module/dace/m/m_dace.js",
                new LocalResource("*/*", "court/js/m_dace.js"));
        // https://dup.baidustatic.com/js/dm.js
        resourceMap.put("https://dup.baidustatic.com/js/dm.js",
                new LocalResource("*/*", "court/js/dm.js"));
        // https://res.wx.qq.com/open/js/jweixin-1.0.0.js
        resourceMap.put("https://res.wx.qq.com/open/js/jweixin-1.0.0.js",
                new LocalResource("*/*", "court/js/jweixin-1.0.0.js"));
        // https://dup.baidustatic.com/tpl/wh.js
        resourceMap.put("https://dup.baidustatic.com/tpl/wh.js",
                new LocalResource("*/*", "court/js/wh.js"));
        // https://dup.baidustatic.com/tpl/fb.js
        resourceMap.put("https://dup.baidustatic.com/tpl/fb.js",
                new LocalResource("*/*", "court/js/fb.js"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/pkg/common_cfd34ae.js
        resourceMap.put("https://assets-m.hoopchina.com.cn/touch/static/common/pkg/common_cfd34ae.js",
                new LocalResource("*/*", "court/js/common_cfd34ae.js"));
        // https://b1.hoopchina.com.cn/common/app-icon-380x380.png
        resourceMap.put("https://b1.hoopchina.com.cn/common/app-icon-380x380.png",
                new LocalResource("image/*", "court/image/app-icon-380x380.png"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/images/ios_a683c89.jpg
        resourceMap.put("https://assets-m.hoopchina.com.cn/touch/static/common/images/ios_a683c89.jpg",
                new LocalResource("image/*", "court/image/ios_a683c89.jpg"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/pkg/common_4c53c37.css
//        resourceMap.put("common_4c53c37.css", new LocalResource("image/*", "court/css/common_4c53c37.css"));
        // https://assets-m.hoopchina.com.cn/touch/static/bbs/bbs/pkg/bbs_detail_dbc8d00.css
//        resourceMap.put("bbs_detail_dbc8d00.css", new LocalResource("image/*", "court/css/bbs_detail_dbc8d00.css"));
        // https://i1.hoopchina.com.cn/touch/622/3598622/touch_adlist_3598622_1513748992.7881.png
        resourceMap.put("https://i1.hoopchina.com.cn/touch/622/3598622/touch_adlist_3598622_1513748992.7881.png",
                new LocalResource("image/*", "court/image/touch_adlist_3598622_1513748992.7881.png"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/images/hupu_favicon.ico
        resourceMap.put("https://assets-m.hoopchina.com.cn/touch/static/common/images/hupu_favicon.ico",
                new LocalResource("image/*", "court/image/hupu_favicon.ico"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/images/icon_ef54db3.png
        resourceMap.put("https://assets-m.hoopchina.com.cn/touch/static/common/images/icon_ef54db3.png",
                new LocalResource("image/*", "court/image/icon_ef54db3.png"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/widget/footer/close_db8b234.png
        resourceMap.put("https://assets-m.hoopchina.com.cn/touch/static/common/widget/footer/close_db8b234.png",
                new LocalResource("image/*", "court/image/close_db8b234.png"));

        // tencent news
        // https://view.inews.qq.com/favicon.ico
        resourceMap.put("https://view.inews.qq.com/favicon.ico",
                new LocalResource("image/*", "news/image/favicon.ico"));
        // https://mat1.gtimg.com/www/js/news/logo-news.png
        resourceMap.put("https://mat1.gtimg.com/www/js/news/logo-news.png",
                new LocalResource("image/*", "news/image/logo-news.png"));
        // https://mat1.gtimg.com/www/js/news/unfold-news.png
        resourceMap.put("https://mat1.gtimg.com/www/js/news/unfold-news.png",
                new LocalResource("image/*", "news/image/unfold-news.png"));
        // https://mat1.gtimg.com/www/js/news/small-pics-news.png
        resourceMap.put("https://mat1.gtimg.com/www/js/news/small-pics-news.png",
                new LocalResource("image/*", "news/image/small-pics-news.png"));
        // https://mat1.gtimg.com/www/js/news/big-video-play.png
        resourceMap.put("https://mat1.gtimg.com/www/js/news/big-video-play.png",
                new LocalResource("image/*", "news/image/big-video-play.png"));
        // https://mat1.gtimg.com/www/js/news/fire2.png
        resourceMap.put("https://mat1.gtimg.com/www/js/news/fire2.png",
                new LocalResource("image/*", "news/image/fire2.png"));
        // https://mat1.gtimg.com/www/js/news/blue-arrow.png
        resourceMap.put("https://mat1.gtimg.com/www/js/news/blue-arrow.png",
                new LocalResource("image/*", "news/image/blue-arrow.png"));
        // https://mat1.gtimg.com/www/js/news/placeholder.png
        resourceMap.put("https://mat1.gtimg.com/www/js/news/placeholder.png",
                new LocalResource("image/*", "news/image/placeholder.png"));
    }

    public static LocalResource get(String url) {
        return resourceMap.get(url);
    }

    public static Set<String> getKeySet() {
        return resourceMap.keySet();
    }

}
