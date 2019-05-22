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
        // https://b3.hoopchina.com.cn/web/module/dace/m/m_dace.js
        resourceMap.put("m_dace.js", new LocalResource("*/*", "court/js/m_dace.js"));
        // https://dup.baidustatic.com/js/dm.js
        resourceMap.put("dm.js", new LocalResource("*/*", "court/js/dm.js"));
        // https://res.wx.qq.com/open/js/jweixin-1.0.0.js
        resourceMap.put("jweixin-1.0.0.js", new LocalResource("*/*", "court/js/jweixin-1.0.0.js"));
        // https://b1.hoopchina.com.cn/common/app-icon-380x380.png
        resourceMap.put("app-icon-380x380.png", new LocalResource("image/*", "court/image/app-icon-380x380.png"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/pkg/common_4c53c37.css
//        resourceMap.put("common_4c53c37.css", new LocalResource("image/*", "court/css/common_4c53c37.css"));
        // https://assets-m.hoopchina.com.cn/touch/static/bbs/bbs/pkg/bbs_detail_dbc8d00.css
//        resourceMap.put("bbs_detail_dbc8d00.css", new LocalResource("image/*", "court/css/bbs_detail_dbc8d00.css"));
        // https://i1.hoopchina.com.cn/touch/622/3598622/touch_adlist_3598622_1513748992.7881.png
        resourceMap.put("touch_adlist_3598622_1513748992.7881.png", new LocalResource("image/*", "court/image/touch_adlist_3598622_1513748992.7881.png"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/images/hupu_favicon.ico
        resourceMap.put("hupu_favicon.ico", new LocalResource("image/*", "court/image/hupu_favicon.ico"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/images/icon_ef54db3.png
        resourceMap.put("icon_ef54db3.png", new LocalResource("image/*", "court/image/icon_ef54db3.png"));
        // https://assets-m.hoopchina.com.cn/touch/static/common/widget/footer/close_db8b234.png
        resourceMap.put("close_db8b234.png", new LocalResource("image/*", "court/image/close_db8b234.png"));
    }

    public static LocalResource get(String url) {
        return resourceMap.get(url);
    }

    public static Set<String> getKeySet() {
        return resourceMap.keySet();
    }

}
