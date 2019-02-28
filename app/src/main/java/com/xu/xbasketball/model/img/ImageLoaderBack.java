package com.xu.xbasketball.model.img;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.xu.xbasketball.R;
import com.xu.xbasketball.app.App;

/**
 * Created by Xu on 2018/4/6.
 *
 * @author Xu
 */
public class ImageLoaderBack {

    public static void load(Activity activity, String url, ImageView iv) {
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).fitCenter().crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void load(Context context, String url, ImageView iv) {
        if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
            Glide.with(context).load(url).fitCenter().crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void load(Context context, String url, ImageView iv, int placeholder) {
        if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
            Glide.with(context).load(url).placeholder(placeholder).fitCenter().crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    // todo 加载图片优化
    public static void load(Context context, String url, int placeholder, SimpleTarget simpleTarget) {
        if (!App.getAppComponent().preferencesHelper().getNoImageState()) {
            Glide.with(context).load(url).asBitmap().placeholder(placeholder).fitCenter().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(simpleTarget);
        }
    }

    /**
     * 不缓存，直接加载图片
     * @param context
     * @param url
     * @param iv
     */
    public static void loadNoCache(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).fitCenter().crossFade().diskCacheStrategy(DiskCacheStrategy.NONE).into(iv);
    }

    public static void loadMipmap(Context context, int mipmapId, ImageView iv) {
        Glide.with(context).load(mipmapId).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(iv);
    }

    public static void pauseImageRequests(Context context) {
        Glide.with(context).pauseRequests();
    }

    public static void resumeImageRequests(Context context) {
        Glide.with(context).resumeRequests();
    }

}
