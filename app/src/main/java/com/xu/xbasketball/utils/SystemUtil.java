package com.xu.xbasketball.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by xu on 2019/03/17.
 *
 * @author xu
 */
public class SystemUtil {

    public static boolean checkPermission(Context context, String permission) {
        PackageManager pm = context.getPackageManager();
        return (PackageManager.PERMISSION_GRANTED ==
                pm.checkPermission(permission, "com.xu.xbasketball"));
    }

}
