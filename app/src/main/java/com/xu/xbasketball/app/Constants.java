package com.xu.xbasketball.app;

import java.io.File;

/**
 * Created by Xu on 2018/3/16.
 */

public class Constants {

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

}
