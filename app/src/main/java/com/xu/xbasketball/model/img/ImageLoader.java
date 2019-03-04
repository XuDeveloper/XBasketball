package com.xu.xbasketball.model.img;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Xu on 2019/2/28.
 *
 * @author Xu
 */
public class ImageLoader {

    private static final String TAG = "ImageLoader";

    private static IBaseImgLoad iBaseImgLoad;

    static {
        iBaseImgLoad = new ImgLoadByGlide();
    }

    public static void load(Context context, String imgUrl, ImageView iv) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.load(context, imgUrl, iv, null, null);
        }
    }

    public static void loadResource(Context context, int resourceId, ImageView iv) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.loadResource(context, resourceId, iv, null, null);
        }
    }

    public static void load(Context context, String imgUrl, ImageView iv, int defaultPlaceholder) {
        ImgConfig imgConfig = new ImgConfig(defaultPlaceholder);
        if (iBaseImgLoad != null) {
            iBaseImgLoad.load(context, imgUrl, iv, imgConfig, null);
        }
    }

    public static void load(Context context, String imgUrl, ImageView iv, ImgConfig imgConfig) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.load(context, imgUrl, iv, imgConfig, null);
        }
    }

    public static void load(Context context, String imgUrl, ImageView iv, ImgConfig imgConfig, ILoadingImg iLoadingImg) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.load(context, imgUrl, iv, imgConfig, iLoadingImg);
        }
    }

    public static void pauseAllImageRequests(Context context) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.pauseAllImgLoad(context);
        }
    }

    public static void pauseImageLoad(Context context, String imgUrl) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.pauseImgLoad(context, imgUrl);
        }
    }

    public static void resumeAllImageRequests(Context context) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.resumeAllImgLoad(context);
        }
    }

    public static void resumeImageLoad(Context context, String imgUrl) {
        if (iBaseImgLoad != null) {
            iBaseImgLoad.resumeImgLoad(context, imgUrl);
        }
    }
}
