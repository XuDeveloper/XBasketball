package com.xu.xbasketball.model.img;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Xu on 2019/2/28.
 *
 * @author Xu
 */
public class ImageLoader {

    public static final String TAG = "ImageLoader";

    private static IBaseImgLoad iBaseImgLoad;

    static {
//        iBaseImgLoad =
    }

    public static void load(Context context, String imgUrl, ImageView iv) {
        iBaseImgLoad.load(context, imgUrl, iv, null, null);
    }

    public static void load(Context context, String imgUrl, ImageView iv, int defaultPlaceholder) {
        ImgConfig imgConfig = new ImgConfig(defaultPlaceholder);
        iBaseImgLoad.load(context, imgUrl, iv, imgConfig, null);
    }

    public static void pauseAllImageRequests(Context context) {
//        iBaseImgLoad.pauseImgLoad(context, );
    }

    public static void pauseImageLoad(Context context, String imgUrl) {
        iBaseImgLoad.pauseImgLoad(context, imgUrl);
    }

    public static void resumeAllImageRequests(Context context) {

    }

    public static void resumeImageLoad(Context context, String imgUrl) {
        iBaseImgLoad.resumeImgLoad(context, imgUrl);
    }

    public static void clearImageView(Context context, ImageView iv, String imgUrl) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.clearImageView(context, iv, imgUrl);
        }
    }
}
