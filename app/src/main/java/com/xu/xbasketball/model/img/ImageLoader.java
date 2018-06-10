package com.xu.xbasketball.model.img;

import android.app.Activity;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * Created by Xu on 2018/4/6.
 *
 * @author Xu
 */
public class ImageLoader {

    public static void load(Activity activity, String url, ImageView iv) {
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).fitCenter().crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
        }
    }

    public static void load(Context context, String url, ImageView iv) {
        Glide.with(context).load(url).fitCenter().crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public static void load(Context context, String url, ImageView iv, int placeholder) {
        Glide.with(context).load(url).placeholder(placeholder).fitCenter().crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(iv);
    }

    public static void load(Context context, String url, int placeholder, SimpleTarget simpleTarget) {
        Glide.with(context).load(url).asBitmap().placeholder(placeholder).fitCenter().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(simpleTarget);
    }

}
