package com.xu.xbasketball.utils;


/**
 * Created by xu on 2018/8/30.
 * <p>
 * js注入，动态修改要加载的网页，使其在webview上更易读
 */
public class JavascriptUtil {

    public static String[] getCourtDetailJsCode() {
        String[] result = new String[2];
        result[0] = "javascript:function clearCourtDetailUnused() {\n" +
                "$('#J-nav-wrap').remove();\n" +
                "$('.m-row').remove();\n" +
                "$('.m-app').remove();\n" +
                "$('.m-reply').remove();\n" +
                "$('.reply-box').remove();\n" +
                "$('.guess-like').remove();\n" +
                "$('.ui-page-tools').remove();\n}";
        result[1] = "javascript:clearCourtDetailUnused()";
        return result;
    }

    public static String[] getNewsDetailJsCode() {
        String[] result = new String[4];
        result[0] = "javascript:function clearNewsDetailUnused() {\n" +
                "var click = document.querySelector('div#root>div:nth-child(1)>div:nth-child(2)>div:nth-child(1)>div:nth-child(2)');\n" +
                "click.click();\n" +
                "var title = document.querySelector('div#root>div:nth-child(1)>div:nth-child(1)');\n" +
                "var content = document.querySelector('div#root>div:nth-child(1)>div:nth-child(2)');\n" +
                "if (title.textContent != null && content.textContent != null) {\n" +
                "document.querySelector('div#root').style.display = 'none';\n" +
                "document.body.appendChild(title);\n" +
                "document.body.appendChild(content);\n}}";
        result[1] = "javascript:function setContentSecurityPolicy() {\n" +
                "var meta = document.createElement('meta');\n" +
                "meta.content = 'upgrade-insecure-requests';\n" +
                "meta.httpEquiv = 'Content-Security-Policy';\n" +
                "document.getElementsByTagName('head')[0].appendChild(meta);\n}";
        result[2] = "javascript:setContentSecurityPolicy();";
        // window.location.reload() 刷新页面
        result[3] = "javascript:let timer = setInterval(() => {\n" +
                "var click = document.querySelector('div#root>div:nth-child(1)>div:nth-child(2)>div:nth-child(1)>div:nth-child(2)');\n" +
                "if (click != null) {\n" +
                "clearInterval(timer);\n" +
                "clearNewsDetailUnused();\n" +
                "setContentSecurityPolicy();}}, 150);\n";
        return result;
    }

}
