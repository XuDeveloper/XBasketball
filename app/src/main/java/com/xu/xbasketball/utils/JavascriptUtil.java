package com.xu.xbasketball.utils;


/**
 * Created by xu on 2018/8/30.
 * <p>
 * js注入，动态修改要加载的网页，使其在webview上更易读
 */
public class JavascriptUtil {

    public static String[] getCourtDetailJsCode() {
        String[] result = new String[2];
        result[0] = "javascript:function courtDetailJsCode() {\n" +
                "$('#J-nav-wrap').remove();\n" +
                "$('.m-row').remove();\n" +
                "$('.m-reply').remove();\n" +
                "$('.reply-box').remove();\n" +
                "$('.guess-like').remove();\n" +
                "$('.ui-page-tools').remove();\n}";
        result[1] = "javascript:courtDetailJsCode()";
        return result;
    }

}
