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
        String[] result = new String[2];
        result[0] = "javascript:function clearNewsDetailUnused() {\n" +
                "var title = document.querySelector(\"div#root>div:nth-child(1)>div:nth-child(1)\");\n" +
                "var content = document.querySelector(\"div#root>div:nth-child(1)>div:nth-child(2)\");\n" +
                "var click = document.querySelector(\"div#root>div:nth-child(1)>div:nth-child(2)>div:nth-child(1)>div:nth-child(2)\");\n" +
                "if (title.textContent != null && content.textContent != null) {\n" +
                "document.querySelector(\"div#root\").style.display = 'none';\n" +
                "document.body.appendChild(title);\n" +
                "document.body.appendChild(content);\n" +
                "click.click();\n}}";
        result[1] = "javascript:clearNewsDetailUnused()";
        return result;
    }

}
