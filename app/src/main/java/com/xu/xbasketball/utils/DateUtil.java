package com.xu.xbasketball.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Xu on 2018/3/27.
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

    public static String getDate(String second) {
        if (second == null) {
            return "";
        }
        Long lt = Long.parseLong(second) * 1000;
        Date date = new Date(lt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static String millisToMinute(int time) {
        long lt = time * 1000;
        Date date = new Date(lt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
        return dateFormat.format(date);
    }

}
