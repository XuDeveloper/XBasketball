package com.xu.xbasketball.app;

import java.io.File;

/**
 * Created by Xu on 2018/3/16.
 */

public class Constants {

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String DEVID = "3a6d5d1eb7c4f376";

    // -----------------------------Intent-----------------------------

    public static final String NEWS_URL = "news_url";

    public static final String NEWS_IMG = "news_img";

    public static final String NEWS_TITLE = "news_title";

}
