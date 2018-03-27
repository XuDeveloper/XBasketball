package com.xu.xbasketball.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaoxuzhang on 2018/3/27.
 */

public class DateUtil {

    /**
     * 获取今天的具体日期，格式20xx-xx-xx
     *
     * @return 20xx-xx-xx
     */
    public static String getTodayDate() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(now);
    }

}
