package com.xu.xbasketball.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by Xu on 2018/3/16.
 */

public class Constants {

    // -----------------------------Path-----------------------------

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "com.xu.xbasketball";

    // -----------------------------Others-----------------------------

    public static final long TIME_INTERNAL = 1000L;

    public static final String FILE_PROVIDER_AUTHORITY = "com.xu.xbasketball.fileprovider";

    // -----------------------------Intent-----------------------------

    public static final String NEWS_URL = "news_url";

    public static final String NEWS_IMG = "news_img";

    public static final String NEWS_TITLE = "news_title";

    public static final String COURT_URL = "court_url";

    public static final String PIC_URL = "pic_url";

    public static final String VIDEO_ID = "video_id";

    public static final String VIDEO_TITLE = "video_title";

    // -----------------------------Preferences-----------------------------

    public static final String SP_NO_IMAGE = "no_image";

    // -----------------------------RecyclerView Adapter-----------------------------

    public static final int TYPE_EMPTY = 1;

}
